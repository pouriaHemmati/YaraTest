package org.pouria.yara.mvp.details;


import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JResSearch;
import org.pouria.yara.mvp.search.ISearchInteractor;
import org.pouria.yara.mvp.search.ISearchPresenter;
import org.pouria.yara.mvp.search.ISearchView;

public class DetailsPresenter implements IDetailsPresenter {
    IDetailsView iDetailsView;
    IDetailsInteractor iDetailsInteractor;


    public DetailsPresenter(IDetailsView iDetailsView, IDetailsInteractor iDetailsInteractor) {
        this.iDetailsView = iDetailsView;
        this.iDetailsInteractor = iDetailsInteractor;
    }

    @Override
    public void callDetails(String id) {
        iDetailsInteractor.Details(id ,  new IDetailsInteractor.IDetailsFinishListener() {
            @Override
            public void successDetails(JDetails jDetails) {
                iDetailsView.successDetails(jDetails);
            }

            @Override
            public void errorDetails(String error) {
                iDetailsView.errorDetails(error);
            }
        });
    }
}

