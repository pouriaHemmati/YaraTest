package org.pouria.yara.retrofit;





import org.pouria.yara.util.Urls;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

    // Search
    @Headers({"Content-Type: application/json"})
    @GET(Urls.SEARCH)
    Observable<ResponseBody> getSearch();

    // Details
    @Headers({"Content-Type: application/json"})
    @GET(Urls.DETAILS)
    Observable<ResponseBody> getDetails(@Query("apikey") String apikey , @Query("i")String id);

}
