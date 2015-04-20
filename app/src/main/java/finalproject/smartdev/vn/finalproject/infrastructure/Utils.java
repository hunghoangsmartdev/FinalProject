package finalproject.smartdev.vn.finalproject.infrastructure;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Locale;

import finalproject.smartdev.vn.finalproject.R;
import finalproject.smartdev.vn.finalproject.constants.Constants;
import finalproject.smartdev.vn.finalproject.view.BaseFragment;
import finalproject.smartdev.vn.finalproject.view.FavoriteFragment;
import finalproject.smartdev.vn.finalproject.view.HomeFragment;
import finalproject.smartdev.vn.finalproject.view.LoginFragment;
import finalproject.smartdev.vn.finalproject.view.ProfileFragment;

/**
 * Created by HUNG-HOANG on 4/18/2015.
 */
public class Utils {
    public static void updateLocaleConfiguration(Context context) {
        changeLocaleConfiguration(context, Locale.US);
    }

    public static void changeLocaleConfiguration(Context context,
                                                 Locale newLocale) {
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = newLocale;
        res.updateConfiguration(conf, dm);
    }
    public static String getLocaleDefault(Context context) {
        return Constants.LOCALE_ENGLISH_LANGUAGE;
    }
    public static void savePreferenceWithKey(Context context, String key, String defValue) {
        SharedPreferences.Editor editor = context.getSharedPreferences(
                Constants.NAME_SHARED_PREFENCES, 0).edit();
        editor.putString(key, defValue);
        editor.commit();
    }

    public static void exitApp(Activity sInstance) {
        int processId = android.os.Process.myPid();
        sInstance.moveTaskToBack(true);
        sInstance.finish();
        sInstance = null;
        android.os.Process.killProcess(processId);
    }
    public static void showMessageDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(R.string.text_title_message));
        builder.setMessage(message);
        builder.setPositiveButton(context.getResources().getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                        dialog.dismiss();
                    }
                });
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void addFragmentAvoidDuplicate(int rootLayout, FragmentManager fragmentManager,
                                                 Fragment fragment, String tag, Bundle bundle) {
        try {
            fragmentManager.executePendingTransactions();
            if (fragmentManager.findFragmentByTag(tag) == null) {
                Log.d("Util", tag + "Fragment null");
            }
            if (fragmentManager.findFragmentByTag(tag) == null
                    || !fragmentManager.findFragmentByTag(tag).isAdded()) {
                if (bundle != null) {
                    fragment.setArguments(bundle);
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.add(rootLayout, fragment, tag);
                transaction.commit();
            }
        } catch (java.lang.IllegalStateException e) {

        }
    }

    public static void onBackPressFragment(BaseFragment fragment, FragmentManager fragmentManager)
    {
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.remove(fragment);
        transaction.commit();
    }

    public static void navigationLoginFragment(FragmentManager fragmentManager,Bundle bundle)
    {
        LoginFragment loginFragment = LoginFragment.getInstance();
        Utils.addFragmentAvoidDuplicate(R.id.root_layout,
                fragmentManager, loginFragment,
                LoginFragment.TAG, bundle);
    }



    public static void navigationHomeFragment(FragmentManager fragmentManager,Bundle bundle)
    {
        HomeFragment homeFragment = HomeFragment.getInstance();
        Utils.addFragmentAvoidDuplicate(R.id.root_layout,
                fragmentManager, homeFragment,
                HomeFragment.TAG, bundle);
    }



    public static void navigationProfileFragment(FragmentManager fragmentManager,Bundle bundle)
    {
        ProfileFragment profileFragment = ProfileFragment.getInstance();
        Utils.addFragmentAvoidDuplicate(R.id.root_layout,
                fragmentManager, profileFragment,
                ProfileFragment.TAG, bundle);
    }

    public static void navigationFavoriteFragment(FragmentManager fragmentManager,Bundle bundle)
    {
        FavoriteFragment favoriteFragment = FavoriteFragment.getInstance();
        Utils.addFragmentAvoidDuplicate(R.id.root_layout,
                fragmentManager, favoriteFragment,
                FavoriteFragment.TAG, bundle);
    }




}
