

package us.qampus.sewakapal;

import android.app.Application;
import buildup.injectors.ApplicationInjector;
import android.support.multidex.MultiDexApplication;
import android.app.Activity;
import android.os.Bundle;
import buildup.util.SecurePreferences;
import us.qampus.sewakapal.ui.LoginActivity;
import buildup.util.LoginUtils;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private SecurePreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        mSharedPreferences = new SecurePreferences(this);
        registerActivityLifecycleCallbacks(this);

        mSharedPreferences.edit().putLong(LoginUtils.EXPIRATION_TIME, LoginUtils.SESSION_EXPIRED).apply();
    }

    public SecurePreferences getSecureSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override
    public void onActivityStarted(Activity activity) {
        boolean splashShown = false;
        if(!splashShown && !(activity instanceof LoginActivity) ){
            LoginUtils.checkLoggedStatus(mSharedPreferences, LoginActivity.class, activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @Override
    public void onActivityStopped(Activity activity) {
        if(!(activity instanceof LoginActivity) ) {
            LoginUtils.storeLastActiveStatus(mSharedPreferences);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


}
