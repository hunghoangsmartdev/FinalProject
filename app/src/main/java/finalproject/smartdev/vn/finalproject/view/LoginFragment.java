package finalproject.smartdev.vn.finalproject.view;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;


import java.util.Arrays;
import java.util.List;

import finalproject.smartdev.vn.finalproject.R;
import finalproject.smartdev.vn.finalproject.infrastructure.Utils;

/**
 * Created by HUNG-HOANG on 4/18/2015.
 */
public class LoginFragment extends  BaseFragment implements View.OnClickListener,FacebookCallback<LoginResult>
,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
    public static final String TAG = "LoginFragment";
    private static LoginFragment instance;
    private static final int RC_SIGN_IN = 0;
    private static final List<String> PERMISSIONS_FACE = Arrays.asList("public_profile", "user_friends");
    private LoginButton btnLoginFB;
    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnLoginGoogle;
    private boolean mIntentInProgress;
    private ConnectionResult mConnectionResult;
    private boolean mSignInClicked;



    public static LoginFragment getInstance() {
        if (instance == null) {
            instance = new LoginFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        View view = inflater.inflate(R.layout.fragment_login,null);
        initViews(view);
        addListenerForViews();
        bindDataOnViews();

        return view;
    }

    @Override
    protected void initViews(View view) {
        btnLoginFB = (LoginButton) view.findViewById(R.id.btnLoginFB);
        btnLoginFB.setReadPermissions(PERMISSIONS_FACE);
        btnLoginFB.setFragment(this);

        btnLoginGoogle = (SignInButton)view.findViewById(R.id.btnLoginGoogle);

    }

    @Override
    protected void addListenerForViews() {
        btnLoginFB.setOnClickListener(this);
        LoginManager.getInstance().registerCallback(callbackManager,this);

        btnLoginGoogle.setOnClickListener(this);
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplicationContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();

    }

    @Override
    protected void bindDataOnViews() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginFB:
                Log.i(TAG, "click btnLoginFB");

                break;
            case R.id.btnLoginGoogle:
                Log.i(TAG, "click btnLoginGoogle");
                googleLogin();
                break;

            default:
                break;

        };

    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        Log.i(TAG,"onSuccess FB"+loginResult);
        Utils.navigationHomeFragment(getActivity().getSupportFragmentManager(),null);
    }

    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(getActivity(), RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }

    private void googleLogin()
    {
        if(!mGoogleApiClient.isConnecting())
        {
            mSignInClicked = true;
            resolveSignInError();
        }
    }



    @Override
    public void onCancel() {
        Log.i(TAG,"onCancel FB");
    }

    @Override
    public void onError(FacebookException e) {
        Log.i(TAG,"onError FB");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG,"onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == 0) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }


    }


    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG,"onConnected Google");
        mSignInClicked =false;
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG,"onConnectionSuspended Google");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG,"onConnectionFailed Google");
       if(! result.hasResolution()){
           GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), getActivity(), 0).show();
            return ;
       }

       if(!mIntentInProgress)
       {
           mConnectionResult = result;
           if(mSignInClicked)
           {
               resolveSignInError();
           }
       }
    }

    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        Log.i(TAG,"onStart Google");
    }

    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        Log.i(TAG,"onStop Google");
    }
}
