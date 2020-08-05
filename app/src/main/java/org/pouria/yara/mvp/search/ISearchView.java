package org.pouria.yara.mvp.search;


import org.pouria.yara.model.JResSearch;
import org.pouria.yara.model.JSearch;

public interface ISearchView {

    void successSearch(JResSearch jResSearch);
    void errorSearch(String error);

}
