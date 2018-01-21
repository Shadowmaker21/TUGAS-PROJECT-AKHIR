package buildup.analytics.injector;

import buildup.analytics.network.LogNetworkLogger;
import buildup.analytics.network.NetworkLogger;

public class NetworkLoggerInjector {

    private static NetworkLogger instance;

    public static NetworkLogger networkLogger() {
        if (instance != null) {
            return instance;
        }
        instance = new LogNetworkLogger();
        return instance;
    }
}
