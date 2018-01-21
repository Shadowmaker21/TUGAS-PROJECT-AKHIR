package buildup.analytics.network;

import buildup.analytics.NetworkResponse;

public interface NetworkLogger {

    void logRequest(String url, String httpMethod);
    void logResponse(NetworkResponse networkResponse);

}
