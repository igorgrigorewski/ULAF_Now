package com.grigorewski.ulafnow.api.response;

import android.content.Context;

import com.grigorewski.ulafnow.content.Team;

import java.util.List;

/**
 * @author Igor Grigorewski
 */
public class StandingsResponse extends Response {
    @Override
    public void save(Context context) {
        List<Team> teams = getTypedAnswer();
        if (teams != null) {
            //AirportsTable.save(context, airports);
        }
    }
}
