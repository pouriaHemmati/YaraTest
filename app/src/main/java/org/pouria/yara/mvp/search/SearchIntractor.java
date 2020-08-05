package org.pouria.yara.mvp.search;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.pouria.yara.model.JResSearch;
import org.pouria.yara.model.JSearch;
import org.pouria.yara.retrofit.ApiService;
import org.pouria.yara.retrofit.ApiServiceManager;
import org.pouria.yara.util.MyGson;


import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class SearchIntractor implements ISearchInteractor {
    @Override
    public void checkSearch(ISearchFinishListener iSearchIFinishedListener) {
        ApiService apiService = ApiServiceManager.getBaseUrl();
        apiService.getSearch()
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
                                JResSearch objModelSe = gson.fromJson(String.valueOf(jsonObj), JResSearch.class);
                                iSearchIFinishedListener.successSearch(objModelSe);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        iSearchIFinishedListener.errorSearch(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }
}
