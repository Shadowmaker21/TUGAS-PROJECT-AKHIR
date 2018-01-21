package buildup.behaviors;

import buildup.analytics.AnalyticsReporter;
import buildup.analytics.injector.AnalyticsReporterInjector;
import buildup.injectors.ApplicationInjector;
import buildup.ui.ListGridFragment;

import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;

/**
 * Pull to refresh pattern for listing fragments
 */
public class AnalyticsSwipeRefreshBehavior extends SwipeRefreshBehavior {

    private AnalyticsReporter analyticsReporter;

    public AnalyticsSwipeRefreshBehavior(ListGridFragment<?> fragment) {
        super(fragment);
        try {
            Class.forName("AnalyticsReporterInjector",
                    false,
                    ClassLoader.getSystemClassLoader()
            );
            analyticsReporter = AnalyticsReporterInjector.analyticsReporter(ApplicationInjector.getApplicationContext());
        } catch (ClassNotFoundException e) {
            analyticsReporter = null;
        }
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        if (analyticsReporter == null) {
            return;
        }
        analyticsReporter.sendEvent(analyticsInfo()
                        .withAction("pullToRefresh")
                        .build().toMap()
        );
    }
}
