package org.pouria.yara.mvp.search.adapter;


import org.pouria.yara.model.JSearch;

import java.util.ArrayList;

public class ModelItemSearch {


    ArrayList<JSearch> jResponse;

    public ModelItemSearch(ArrayList<JSearch> jResponse) {
        this.jResponse = jResponse;

    }


    public void setResponse(ArrayList<JSearch> jResponse) {
        this.jResponse = jResponse;
    }

    public ArrayList<JSearch> getResponse() {
        return jResponse;
    }
}


