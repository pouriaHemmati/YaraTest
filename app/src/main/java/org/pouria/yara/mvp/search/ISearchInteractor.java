package org.pouria.yara.mvp.search;


import org.pouria.yara.model.JResSearch;
import org.pouria.yara.model.JSearch;

public interface ISearchInteractor {

    void checkSearch( ISearchFinishListener iSearchFinishListener);
    interface ISearchFinishListener{
        void successSearch(JResSearch jResSearch);
        void errorSearch(String error);
    }

}
