package org.pouria.yara.base;

import android.app.Activity;
import android.content.Context;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;

import androidx.multidex.MultiDexApplication;

import java.util.logging.Handler;

public class BaseActivity extends MultiDexApplication {

    private static BaseActivity instance = null;
    private static Context context;
    private static Activity currentActivity;
    private static Handler handler;
    private static LayoutInflater layoutInflater;
    private static TransitionInflater transitionInflater;
    public static final String TAG = BaseActivity.class.getSimpleName();



    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();




    }





    public static Context getContext() {
        if (currentActivity != null) {
            return currentActivity;
        }
        return context;
    }

    public static Context getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity activity) {
        currentActivity = activity;
    }

    public static synchronized BaseActivity getInstance() {
        if (instance == null) {
            instance = new BaseActivity();
        }
        return instance;
    }



}
