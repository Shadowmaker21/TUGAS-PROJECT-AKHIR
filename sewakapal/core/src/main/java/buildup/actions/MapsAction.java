package buildup.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import buildup.analytics.model.AnalyticsInfo;
import buildup.core.R;

import static android.content.Intent.createChooser;
import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

public class MapsAction implements Action {
    private static final String MAPS_URL = "http://maps.google.com/maps?q=";
    private final IntentLauncher intentLauncher;
    private String uri;

    public MapsAction(IntentLauncher intentLauncher, String uri) {
        this.intentLauncher = intentLauncher;

        if(uri != null && !uri.equals("")) {
            this.uri = MAPS_URL + uri;
        }
    }

    @Override
    public void execute(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        intentLauncher.start(context,
                createChooser(intent, context.getString(R.string.find_on_map))
        );
    }

    @Override
    public boolean canDoExecute() {
        return uri != null;
    }

    @NonNull
    @Override
    public AnalyticsInfo getAnalyticsInfo() {
        return analyticsInfo()
                .withAction("Find on map")
                .withTarget(uri)
                .build();
    }
}
