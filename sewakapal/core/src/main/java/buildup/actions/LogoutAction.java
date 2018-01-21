package buildup.actions;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;
import buildup.util.LoginUtils;
import buildup.util.SecurePreferences;

import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

public class LogoutAction implements Action {
    private SecurePreferences mSharedPreferences;
    private Class loginActivity;
    private Activity activity;

    public LogoutAction(SecurePreferences mSharedPreferences, Class loginActivity, Activity activity) {
        this.mSharedPreferences = mSharedPreferences;
        this.loginActivity = loginActivity;
        this.activity = activity;
    }

    @Override
    public void execute(@NonNull Context context) {
        LoginUtils.logOut(mSharedPreferences,
                loginActivity,
                activity
        );
    }

    @Override
    public boolean canDoExecute() {
        return true;
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Log out")
                .withTarget("Log out")
                .build();
    }
}
