package org.pouria.yara.mvp.search;


import org.pouria.yara.model.JResSearch;
import org.pouria.yara.model.JSearch;

public class SearchPresenter implements ISearchPresenter {
    ISearchView iSearchView;
    ISearchInteractor iSearchInteractor;

    public SearchPresenter(ISearchView iSearchView, ISearchInteractor iSearchInteractor) {
        this.iSearchView = iSearchView;
        this.iSearchInteractor = iSearchInteractor;
    }

    @Override
    public void callSearch() {
        iSearchInteractor.checkSearch( new ISearchInteractor.ISearchFinishListener() {
            @Override
            public void successSearch(JResSearch jResSearch) {
                iSearchView.successSearch(jResSearch);
            }

            @Override
            public void errorSearch(String error) {
                iSearchView.errorSearch(error);
            }
        });
    }
}

