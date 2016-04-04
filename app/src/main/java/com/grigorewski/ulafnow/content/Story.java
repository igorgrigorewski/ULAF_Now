package com.grigorewski.ulafnow.content;

import com.google.gson.annotations.SerializedName;

/**
 * @author Igor Grigorewski
 */

public class Story {

    @SerializedName("NewsID")
    private int newsID;

    @SerializedName("Title")
    private String title;

    @SerializedName("Updated")
    private String updated;

    @SerializedName("Url")
    private String url;

    @SerializedName("Content")
    private String content;

    @SerializedName("Source")
    private String source;

    public Story( ){

    }

    public Story(int newsID, String title, String updated, String url, String content, String source){
        this.newsID = newsID;
        this.title = title;
        this.updated = updated;
        this.url = url;
        this.content = content;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
