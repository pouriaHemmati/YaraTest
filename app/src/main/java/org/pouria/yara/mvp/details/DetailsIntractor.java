package org.pouria.yara.mvp.details;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JResSearch;
import org.pouria.yara.mvp.search.ISearchInteractor;
import org.pouria.yara.retrofit.ApiService;
import org.pouria.yara.retrofit.ApiServiceManager;
import org.pouria.yara.util.MyGson;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DetailsIntractor implements IDetailsInteractor {
    private String apikey = "3e974fca";
    @Override
    public void Details(String id  ,IDetailsFinishListener iDetailsIFinishedListener) {
        ApiService apiService = ApiServiceManager.getBaseUrl();
        apiService.getDetails(apikey , id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            try {
                                JSONObject jsonObj = new JSONObject(responseBody.string());
                                Gson gson = MyGson.create();
                                JDetails objModelSe = gson.fromJson(String.valueOf(jsonObj), JDetails.class);
                                iDetailsIFinishedListener.successDetails(objModelSe);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iDetailsIFinishedListener.errorDetails(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
