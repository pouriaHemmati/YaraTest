package org.pouria.yara.mvp.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.pouria.yara.R;
import org.pouria.yara.base.BaseActivity;
import org.pouria.yara.local.MyDataBaseSearch;
import org.pouria.yara.model.JResSearch;
import org.pouria.yara.model.JSearch;
import org.pouria.yara.mvp.details.DetailsActivity;
import org.pouria.yara.mvp.search.adapter.IOnClickMovie;
import org.pouria.yara.mvp.search.adapter.ModelItemSearch;
import org.pouria.yara.mvp.search.adapter.SearchRecyclerAdapter;
import org.pouria.yara.util.NetworkUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements ISearchView, IOnClickMovie {
    SearchPresenter searchPresenter;
    @BindView(R.id.rv_search)
    RecyclerView rv_search;
    @BindView(R.id.lay_notFound)
    ConstraintLayout lay_notFound;
    ModelItemSearch modelItemSearch;
    MyDataBaseSearch dataBase;
    ArrayList<JSearch> jSearches;
    ArrayList<JSearch> AddjSearches;
    ArrayList<JSearch> rvJSearches = new ArrayList<>();
    SearchRecyclerAdapter searchRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        searchPresenter = new SearchPresenter(this, new SearchIntractor());
        dataBase = Room.databaseBuilder(BaseActivity.getContext(), MyDataBaseSearch.class, "SearchDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        request();
        jSearches = new ArrayList<>();
        AddjSearches = new ArrayList<>();
    }

    // request
    private void request() {
        if (NetworkUtils.isConnected(BaseActivity.getContext())) {
            searchPresenter.callSearch();
        } else {
            if (dataBase.searchDao().getAll().size() > 0) {
                rvJSearches = (ArrayList<JSearch>) dataBase.searchDao().getAll();
                recyclerSearch(rvJSearches);
            } else {
                lay_notFound.setVisibility(View.VISIBLE);
                Toast.makeText(BaseActivity.getContext(), "اطلاعاتی یافت نشد",
                        Toast.LENGTH_LONG).show();
            }
        }

    }

    // click try
    @OnClick(R.id.btn_try)
    public void btn_try() {
        request();

    }

    @Override
    public void successSearch(JResSearch jResSearch) {
        lay_notFound.setVisibility(View.GONE);
        recyclerSearch(jResSearch.Search);
        jSearches = (ArrayList<JSearch>) dataBase.searchDao().getAll();
        if (jSearches.size() > 0) {
            for (int i = 0; i < jResSearch.Search.size(); i++) {
                boolean addedBefore = false;
                for (int cp = 0; cp < jResSearch.Search.size(); cp++) {
                    if (jSearches.get(cp).imdbID.equals(jResSearch.Search.get(i).imdbID)) {
                        addedBefore = true;
                        break;
                    }
                }
                if (!addedBefore)
                    AddjSearches.add(jResSearch.Search.get(i));
            }
            if (AddjSearches.size() > 0)
                dataBase.searchDao().searchAll(AddjSearches);
        } else {
            dataBase.searchDao().searchAll(jResSearch.Search);
        }


    }

    @Override
    public void errorSearch(String error) {
        Toast.makeText(BaseActivity.getContext(), error,
                Toast.LENGTH_LONG).show();
    }

    // set recycler
    private void recyclerSearch(ArrayList<JSearch> jSearchArrayList) {
        modelItemSearch = new ModelItemSearch(jSearchArrayList);
        searchRecyclerAdapter = new SearchRecyclerAdapter(BaseActivity.getContext(), modelItemSearch, this);
        rv_search.setLayoutManager(new LinearLayoutManager(BaseActivity.getContext(), LinearLayoutManager.VERTICAL, false));
        rv_search.setAdapter(searchRecyclerAdapter);

    }

    @Override
    public void onClickMovie(String imdbID) {
        startActivityBundle(DetailsActivity.class, "imdbID", imdbID);
    }

    /**
     * by pouria
     *
     * @param otherActivityClass second class start
     * @param keyValue           key value bundle
     * @param bundle             your bundle
     */
    private void startActivityBundle(Class<?> otherActivityClass, String keyValue, String bundle) {
        Intent i = new Intent(BaseActivity.getContext(), otherActivityClass);
        i.putExtra(keyValue, bundle);
        startActivity(i);
    }
}