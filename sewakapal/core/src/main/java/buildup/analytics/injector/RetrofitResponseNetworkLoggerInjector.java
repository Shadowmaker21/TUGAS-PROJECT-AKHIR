package buildup.analytics.injector;

import buildup.analytics.network.RetrofitResponseNetworkLogger;

import static buildup.analytics.injector.NetworkLoggerInjector.networkLogger;

public class RetrofitResponseNetworkLoggerInjector {

    public static RetrofitResponseNetworkLogger retrofitNetworkLogger() {
        return new RetrofitResponseNetworkLogger(networkLogger());
    }
}
