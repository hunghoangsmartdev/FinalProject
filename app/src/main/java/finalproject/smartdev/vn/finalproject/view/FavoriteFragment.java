package finalproject.smartdev.vn.finalproject.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalproject.smartdev.vn.finalproject.R;

/**
 * Created by HUNG-HOANG on 4/18/2015.
 */
public class FavoriteFragment extends  BaseFragment {

    public static final String TAG = "FavoriteFragment";
    private static FavoriteFragment instance;


    public static FavoriteFragment getInstance() {
        if (instance == null) {
            instance = new FavoriteFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,null);
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
