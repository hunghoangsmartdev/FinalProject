package finalproject.smartdev.vn.finalproject.view;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;

import finalproject.smartdev.vn.finalproject.infrastructure.Utils;

/**
 * Created by HUNG-HOANG on 4/18/2015.
 */
public abstract class BaseFragment extends Fragment {
    protected abstract void initViews(View view);
    protected abstract void addListenerForViews();
    protected abstract void bindDataOnViews();
    protected  void onBackPress(){
        Utils.onBackPressFragment(this, getActivity().getSupportFragmentManager());
    }
    protected void showMessageInfo(Context context, String messageCode, String messageInfo,
                                   String messageInfoTwo) {
        int valueMessageCode = Integer.parseInt(messageCode);
        if (valueMessageCode >= 5000) {
            Utils.showMessageDialog(context, messageInfo);
        } else {
            Utils.showMessageDialog(context, messageInfoTwo);

        }
    }
}
