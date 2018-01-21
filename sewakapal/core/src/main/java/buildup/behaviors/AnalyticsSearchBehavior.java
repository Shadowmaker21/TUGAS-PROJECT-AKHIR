package buildup.behaviors;

import buildup.analytics.AnalyticsReporter;
import buildup.ui.Filterable;

import static buildup.analytics.injector.AnalyticsReporterInjector.analyticsReporter;
import static buildup.analytics.model.AnalyticsInfo.Builder.analyticsInfo;
import static buildup.injectors.ApplicationInjector.getApplicationContext;

/**
 * Adds an action bar search interface
 */
public class AnalyticsSearchBehavior extends SearchBehavior {

    private final AnalyticsReporter analyticsReporter;
    private final String datasource;

    public AnalyticsSearchBehavior(Filterable f, String datasource) {
        super(f);
        this.datasource = datasource;
        analyticsReporter = analyticsReporter(getApplicationContext());

    }

    public void refreshSearch(String newFilter) {
        super.refreshSearch(newFilter);

        analyticsReporter.sendEvent(analyticsInfo()
                        .withAction("search")
                        .withTarget(newFilter)
                        .withDataSource(datasource)
                        .build().toMap()
        );
    }
}
