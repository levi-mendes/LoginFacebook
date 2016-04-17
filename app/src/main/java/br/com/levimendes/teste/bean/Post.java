package br.com.levimendes.teste.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Levi on 16/04/2016.
 */
public class Post implements Serializable {

    @SerializedName("id")
    public String id;
    @SerializedName("created_time")
    public String createdTime;
    @SerializedName("message")
    public String message;
    @SerializedName("story")
    public String story;

    public Post(String id, String createdTime, String message, String story) {
        this.id          = id;
        this.createdTime = createdTime;
        this.message     = message;
        this.story       = story;
    }
}
