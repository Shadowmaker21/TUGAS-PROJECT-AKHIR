package buildup.injectors;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import buildup.util.image.BasicAuthInterceptor;

public class PicassoBuilderInjector {
    private static Picasso picassoBuilderInjector;

    public static void setPicassoBasicAuthBuilderInjector(BasicAuthInterceptor basicAuthInterceptor, Context context) {
        Picasso.Builder builder = new Picasso.Builder(context);

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(basicAuthInterceptor);

        Downloader downloader = new OkHttpDownloader(client);

        picassoBuilderInjector = builder.downloader(downloader).build();
    }

    public static Picasso getPicassoBuilderInjector() {
        return picassoBuilderInjector;
    }
}
