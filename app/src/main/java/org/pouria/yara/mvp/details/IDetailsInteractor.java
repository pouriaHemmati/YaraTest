package org.pouria.yara.mvp.details;


import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JResSearch;

public interface IDetailsInteractor {

    void Details(String id , IDetailsFinishListener iDetailsFinishListener);
    interface IDetailsFinishListener{
        void successDetails( JDetails jDetails);
        void errorDetails(String error);
    }

}
