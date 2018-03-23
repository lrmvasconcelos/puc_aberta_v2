package pucaberta.pucminas.com.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import pucaberta.pucminas.com.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lucas on 02/03/2018.
 * Update at 02/03/2018
 */

public class RetrofitBase {

    private static RestApi interfaceRetrofit;
    private Retrofit retrofit;

    private RetrofitBase() {
        initRetrofit();
    }

    public static RestApi getInterfaceRetrofit() {
        if (interfaceRetrofit == null) new RetrofitBase();
        return interfaceRetrofit;
    }

    private static void fillAuthHeaders(Request.Builder builder) {

//        if (NitroSellApp.prefs.getInt(Constants.HEADER.COMPANY_ID, 0) != 0) {
//            builder.addHeader(Constants.HEADER.COMPANY_ID, String.valueOf(NitroSellApp.prefs.getInt(Constants.HEADER.COMPANY_ID)));
//        }
//
//        if (getAccessToken() != null)
//            builder.addHeader(Constants.SharedPreferences.HEADER_ACCESS_TOKEN, getAccessToken());
//
//        if (getUserId() != null)
//            builder.addHeader(Constants.SharedPreferences.HEADER_UID, getUserId());
//
//        if (getClient() != null)
//            builder.addHeader(Constants.SharedPreferences.HEADER_CLIENT, getClient());
    }

    private static void updateAuthHeaders(Headers headers) {
//        if (headers.get(Constants.SharedPreferences.HEADER_ACCESS_TOKEN) != null)
//            setAccessToken(headers.get(Constants.SharedPreferences.HEADER_ACCESS_TOKEN));
//
//        if (headers.get(Constants.SharedPreferences.HEADER_UID) != null)
//            setUserId(headers.get(Constants.SharedPreferences.HEADER_UID));
//
//        if (headers.get(Constants.SharedPreferences.HEADER_CLIENT) != null)
//            setClient(headers.get(Constants.SharedPreferences.HEADER_CLIENT));
    }

    private static void setAccessToken(String accessToken) {
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_ACCESS_TOKEN, accessToken);
    }

    private static void setUserId(String userId) {
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_UID, userId);
    }

//    private static String getAccessToken() {
//        return NitroSellApp.prefs.getString(Constants.SharedPreferences.HEADER_ACCESS_TOKEN);
//    }

    private static void setClient(String client) {
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_CLIENT, client);
    }

//    private static String getUserId() {
//        return NitroSellApp.prefs.getString(Constants.SharedPreferences.HEADER_UID);
//    }

    public static void removeAllAuth() {
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_ACCESS_TOKEN, " ");
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_CLIENT, " ");
//        NitroSellApp.prefs.setValue(Constants.SharedPreferences.HEADER_UID, " ");
//
//        NitroSellApp.prefs.remove(Constants.SharedPreferences.HEADER_ACCESS_TOKEN);
//        NitroSellApp.prefs.remove(Constants.SharedPreferences.HEADER_CLIENT);
//        NitroSellApp.prefs.remove(Constants.SharedPreferences.HEADER_UID);
    }

//    private static String getClient() {
//        return NitroSellApp.prefs.getString(Constants.SharedPreferences.HEADER_CLIENT);
//    }

    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(1, TimeUnit.HOURS);
        builder.connectTimeout(60, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logInterceptor);
        }

        GsonBuilder gsonBuilder = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);

//        gsonBuilder.registerTypeAdapter(TypePayment.class, new TypePaymentDeserializer());
        Gson gson = gsonBuilder.create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        addAuthorizationHeaders(builder);

        String urlBase = BuildConfig.BASE_URL;

        OkHttpClient httpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(urlBase)
                .client(httpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();

        interfaceRetrofit = retrofit.create(RestApi.class);
    }

    private void addAuthorizationHeaders(final OkHttpClient.Builder builder) {
        builder.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder request = original
                    .newBuilder()
                    .method(original.method(), original.body());
            fillAuthHeaders(request);

            Response response = chain.proceed(request.build());

            updateAuthHeaders(response.headers());

            return response;
        });
    }
}
