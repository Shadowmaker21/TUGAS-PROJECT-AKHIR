package buildup.behaviors;

import android.view.MenuItem;

import buildup.analytics.AnalyticsReporter;
import buildup.analytics.injector.AnalyticsReporterInjector;
import buildup.ui.Refreshable;

import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;
import static buildup.injectors.ApplicationInjector.getApplicationContext;

/**
 * Add refresh pattern to lists
 */
public class AnalyticsRefreshBehavior extends RefreshBehavior {

    private final AnalyticsReporter analyticsReporter;
    private final String datasource;

    public AnalyticsRefreshBehavior(Refreshable fragment, String datasource) {
        super(fragment);
        this.datasource = datasource;
        analyticsReporter = AnalyticsReporterInjector.analyticsReporter(getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = super.onOptionsItemSelected(item);
        if (handled) {
            analyticsReporter.sendEvent(analyticsInfo()
                            .withAction("refresh")
                            .withDataSource(datasource)
                            .build().toMap()
            );
        }
        return handled;
    }
}
