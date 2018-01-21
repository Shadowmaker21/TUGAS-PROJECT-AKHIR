package buildup.analytics.injector;

import android.content.Context;
import android.util.Log;

import java.util.Properties;

import buildup.analytics.AnalyticsReporter;
import buildup.analytics.LogAnalyticsReporter;

public class AnalyticsReporterInjector {

    private final static AnalyticsReporter LOG_ANALYTICS_REPORTER = new LogAnalyticsReporter();

    public static AnalyticsReporter analyticsReporter() {
        return LOG_ANALYTICS_REPORTER;
    }

    public static AnalyticsReporter analyticsReporter(Context context) {
        return LOG_ANALYTICS_REPORTER;
    }
}
