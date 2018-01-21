package buildup.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;
import buildup.core.R;

import static android.content.Intent.createChooser;
import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

public class OpenUriAction implements Action {

    private Uri uri;
    private final IntentLauncher intentLauncher;


    public OpenUriAction(IntentLauncher intentLauncher, @NonNull String uri) {
        this.intentLauncher = intentLauncher;

        if(uri != null && !uri.equals("")) {
            Uri parsedUri = Uri.parse(uri);
            if (parsedUri.getScheme() == null) {
                parsedUri = Uri.parse("http://" + uri);
            }
            this.uri = parsedUri;
        }
    }

    @Override
    public void execute(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intentLauncher.start(context, createChooser(intent, context.getString(R.string.open_url)));
    }

    @Override
    public boolean canDoExecute() {
        return uri != null;
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Open link")
                .withTarget(uri.toString())
                .build();
    }

}
