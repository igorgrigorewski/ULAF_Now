package com.grigorewski.ulafnow.loaders;

import android.content.Context;

import com.grigorewski.ulafnow.api.ApiFactory;
import com.grigorewski.ulafnow.api.StandingsService;
import com.grigorewski.ulafnow.api.response.RequestResult;
import com.grigorewski.ulafnow.api.response.Response;
import com.grigorewski.ulafnow.api.response.StandingsResponse;
import com.grigorewski.ulafnow.api.response.StoriesResponse;
import com.grigorewski.ulafnow.content.Team;

import java.io.IOException;
import java.util.List;

import retrofit.Call;

/**
 * @author Igor Grigorewski
 */
public class StandingsLoader extends BaseLoader {

    public StandingsLoader(Context context) {
        super(context);
    }

    @Override
    protected Response apiCall() throws IOException {
        StandingsService service = ApiFactory.getStandingsService();
        Call<List<Team>> call = service.teams();
        List<Team> teams = call.execute().body();
        return new StandingsResponse()
                .setRequestResult(RequestResult.SUCCESS)
                .setAnswer(teams);
    }
}
