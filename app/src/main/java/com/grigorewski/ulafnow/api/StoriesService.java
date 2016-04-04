package com.grigorewski.ulafnow.api;

import com.grigorewski.ulafnow.content.Story;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * @author Igor Grigorewski
 */
public interface StoriesService {

    @Headers("Ocp-Apim-Subscription-Key: 6219372b1b8d4702beb378a8890e9a96")
    @GET("/nfl/v2/json/news")
    Call<List<Story>> stories();
}
