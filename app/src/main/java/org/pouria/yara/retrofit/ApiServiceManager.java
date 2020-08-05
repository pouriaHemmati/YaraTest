package org.pouria.yara.retrofit;


import org.pouria.yara.util.Urls;

public class ApiServiceManager {

    public static ApiService getBaseUrl() {
        return RetrofitClient.getRetrofit(Urls.BASE_URL,"BaseUrl").create(ApiService.class);
    }
}
