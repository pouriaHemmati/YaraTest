package org.pouria.yara.mvp.details;


import org.pouria.yara.model.JDetails;
import org.pouria.yara.model.JResSearch;

public interface IDetailsView {

    void successDetails(JDetails jDetails);
    void errorDetails(String error);

}
