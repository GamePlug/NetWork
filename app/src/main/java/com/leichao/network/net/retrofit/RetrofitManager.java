package com.leichao.network.net.retrofit;

import com.leichao.network.config.NetConfig;
import com.leichao.network.net.okhttp.ClientForRetrofit;
import com.leichao.network.net.okhttp.ProgressListener;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Retrofit的工具类
 * Created by leichao on 2016/4/20.
 */
public class RetrofitManager {

    public static final class Creator {
        private OkHttpClient client;
        private String baseUrl;
        private Converter.Factory factory;
        private ProgressListener downListener;

        public Creator client(OkHttpClient client) {
            this.client = client;
            return this;
        }

        public Creator baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Creator factory(Converter.Factory factory) {
            this.factory = factory;
            return this;
        }

        public Creator downListener(ProgressListener downListener) {
            this.downListener = downListener;
            return this;
        }

        public  <T> T  create(final Class<T> service) {
            if (client == null) {
                if (downListener != null) {
                    client = ClientForRetrofit.newClient(downListener);
                } else {
                    client = ClientForRetrofit.getClient();
                }
            }
            if (baseUrl == null) {
                baseUrl = NetConfig.DEFAULT_BASEURL;
            }
            if (factory == null) {
                factory = NetConfig.DEFAULT_FACTORY;
            }
            return new Retrofit.Builder()
                    .client(client)
                    .baseUrl(baseUrl)
                    .addConverterFactory(factory)
                    .build()
                    .create(service);
        }
    }

}
