package com.abdullah.goodmovies4.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieGet {

    @SerializedName("page")
    private String page;
    @SerializedName("total_results")
    private String total_results;
    @SerializedName("total_pages")
    private String total_pages;
    @SerializedName("results")
    private ArrayList<Movie> results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
