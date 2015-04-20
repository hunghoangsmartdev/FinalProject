
package finalproject.smartdev.vn.finalproject.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import com.stericson.RootTools.RootTools;
import java.util.Timer;
import java.util.TimerTask;
import finalproject.smartdev.vn.finalproject.R;
import finalproject.smartdev.vn.finalproject.constants.Constants;
import finalproject.smartdev.vn.finalproject.infrastructure.Utils;

public class SplashActivity extends Activity {

    private long splashDelay = 3000;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Utils.updateLocaleConfiguration(getApplicationContext());
        String currentLocaleLanguage = Utils.getLocaleDefault(this);
        Utils.savePreferenceWithKey(this, Constants.LOCALE_LANGUAGE, currentLocaleLanguage);

        if (!RootTools.isRootAvailable()) {
            showWarningAppRooted();
        } else {
            showAnimationSplash();
        }
    }


    private void showWarningAppRooted() {
        AlertDialog.Builder warningDialog = new AlertDialog.Builder(this);
        warningDialog.setTitle(R.string.error);
        warningDialog.setMessage(R.string.error_root);
        warningDialog.setCancelable(false);
        warningDialog.setNeutralButton(R.string.ok, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Utils.exitApp(SplashActivity.this);
            }
        });
        warningDialog.show();
    }

    private void showAnimationSplash() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent mainIntent = new Intent().setClass(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
            }
        };
        timer = new Timer();
        timer.schedule(task, splashDelay);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (timer != null) {
                timer.cancel();
            }
            Utils.exitApp(this);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
