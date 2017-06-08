package com.optimise.appbutton.model;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by anoop.singh on 11-May-17.
 */

public class ItemProperties {
    private List<String> keyword;

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    /**
     *
     * @return
     */
    public JSONArray getJson(){
        JSONArray jsonArray = null;
        if(keyword != null && keyword.size() > 0){
            for(String key : keyword){
                jsonArray.put(key);
            }
        }

        return jsonArray;
    }
}
