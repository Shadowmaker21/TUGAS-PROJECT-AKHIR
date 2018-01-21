package buildup.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;
import buildup.core.R;

import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

/**
 * Play video action
 */
public class VideoAction implements Action {

    private final IntentLauncher intentLauncher;
    private String link;

    public VideoAction(IntentLauncher intentLauncher, String link) {
        if(link != null && !link.equals("")) {
            this.link = link;
        }

        this.intentLauncher = intentLauncher;
    }

    @Override
    public void execute(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

        intentLauncher.start(context, Intent.createChooser(intent, context.getString(R.string.play_video)));
    }

    @Override
    public boolean canDoExecute() {
        return link != null;
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Play video")
                .withTarget(link)
                .build();
    }
}
