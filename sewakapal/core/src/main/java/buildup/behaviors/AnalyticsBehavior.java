package buildup.behaviors;


import buildup.analytics.AnalyticsReporter;
import buildup.analytics.model.AnalyticsInfo;

public class AnalyticsBehavior extends NoOpBehavior {

    private final AnalyticsReporter analyticsReporter;
    private final String pageName;

    public AnalyticsBehavior(AnalyticsReporter analyticsReporter, String pageName) {
        this.analyticsReporter = analyticsReporter;
        this.pageName = pageName;
    }

    @Override
    public void onStart() {
        analyticsReporter.sendView(pageName);
    }

    @Override
    public void onActionClick(AnalyticsInfo action) {
        analyticsReporter.sendEvent(action.toMap());
    }
}
