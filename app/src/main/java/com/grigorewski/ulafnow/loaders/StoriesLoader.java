package com.grigorewski.ulafnow.loaders;

import android.content.Context;

import com.grigorewski.ulafnow.api.ApiFactory;
import com.grigorewski.ulafnow.api.response.RequestResult;
import com.grigorewski.ulafnow.api.response.Response;
import com.grigorewski.ulafnow.api.response.StoriesResponse;
import com.grigorewski.ulafnow.api.StoriesService;
import com.grigorewski.ulafnow.content.Story;

import java.io.IOException;
import java.util.List;

import retrofit.Call;

/**
 * @author Igor Grigorewski
 */
public class StoriesLoader extends BaseLoader {

    public StoriesLoader(Context context) {
        super(context);
    }

    @Override
    protected Response apiCall() throws IOException {
        StoriesService service = ApiFactory.getStoriesService();
        Call<List<Story>> call = service.stories();
        List<Story> stories = call.execute().body();
        return new StoriesResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(stories);
    }
}
