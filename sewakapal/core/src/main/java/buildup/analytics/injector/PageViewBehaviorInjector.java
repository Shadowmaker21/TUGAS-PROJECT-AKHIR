package buildup.analytics.injector;

import buildup.behaviors.AnalyticsBehavior;
import buildup.behaviors.Behavior;
import buildup.injectors.ApplicationInjector;

import static buildup.analytics.injector.AnalyticsReporterInjector.analyticsReporter;
import static buildup.injectors.ApplicationInjector.getApplicationContext;

public class PageViewBehaviorInjector {

    public static Behavior pageViewBehavior(String pageName) {
        return new AnalyticsBehavior(analyticsReporter(getApplicationContext()), pageName);
    }
}
