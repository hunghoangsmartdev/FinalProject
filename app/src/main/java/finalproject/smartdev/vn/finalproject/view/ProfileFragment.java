package finalproject.smartdev.vn.finalproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import finalproject.smartdev.vn.finalproject.R;

/**
 * Created by HUNG-HOANG on 4/18/2015.
 */
public class ProfileFragment extends BaseFragment {

    public static final String TAG = "ProfileFragment";
    private static ProfileFragment instance;


    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,null);
        initViews(view);
        addListenerForViews();
        bindDataOnViews();

        return view;
    }

    @Override
    protected void initViews(View view) {

    }

    @Override
    protected void addListenerForViews() {

    }

    @Override
    protected void bindDataOnViews() {

    }


}
