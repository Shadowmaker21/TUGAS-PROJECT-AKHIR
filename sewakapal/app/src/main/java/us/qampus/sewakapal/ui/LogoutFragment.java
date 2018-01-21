package us.qampus.sewakapal.ui;

import android.os.Bundle;

import us.qampus.sewakapal.MyApplication;

import buildup.ui.BaseFragment;
import buildup.util.LoginUtils;

public class LogoutFragment extends BaseFragment {

    public static LogoutFragment newInstance(Bundle bundle) {
        return new LogoutFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginUtils.logOut(((MyApplication) getActivity().getApplication()).getSecureSharedPreferences(),
                LoginActivity.class,
                getActivity()
        );
    }
}
