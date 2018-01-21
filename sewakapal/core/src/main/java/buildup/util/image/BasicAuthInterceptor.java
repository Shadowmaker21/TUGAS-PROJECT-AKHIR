package buildup.util.image;

import android.util.Base64;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class BasicAuthInterceptor implements Interceptor {
    String username;
    String password;

    public BasicAuthInterceptor(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String basicAuth = Base64.encodeToString(String.format("%s:%s", username, password).getBytes(), Base64.NO_WRAP);

        Request compressedRequest = chain.request().newBuilder()
                .header("Authorization", "Basic " + basicAuth)
                .build();

        return chain.proceed(compressedRequest);
    }
}