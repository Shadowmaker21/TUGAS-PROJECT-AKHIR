package buildup.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;
import buildup.core.R;

import static android.content.Intent.createChooser;
import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

/**
 * Mail sender action
 */
public class MailAction implements Action {

    private String mail;
    private final IntentLauncher intentLauncher;

    public MailAction(IntentLauncher intentLauncher, String mail) {
        this.intentLauncher = intentLauncher;

        if (mail != null && !mail.equals("")) {
            if(!mail.startsWith("mailto:")) {
                this.mail = "mailto:" + mail;
            } else {
                this.mail = mail;
            }
        }
    }

    @Override
    public void execute(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(mail));

        intentLauncher.start(context,
                createChooser(intent, context.getString(R.string.send_email))
        );
    }

    @Override
    public boolean canDoExecute() {
        return mail != null;
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Send email")
                .withTarget(mail)
                .build();
    }
}
