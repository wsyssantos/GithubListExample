package com.example.wesley.githublist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wesley on 9/15/16.
 */
public class RepositoryListResponse {
    @SerializedName("total_count")
    private int count;
    private List<Repository> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Repository> getItems() {
        return items;
    }

    public void setItems(List<Repository> items) {
        this.items = items;
    }
}
