package com.grigorewski.ulafnow.api.response;

import android.content.Context;

import com.grigorewski.ulafnow.content.Story;

import java.util.List;

/**
 * @author Igor Grigorewski
 */
public class StoriesResponse extends Response {
    @Override
    public void save(Context context) {
        List<Story> stories = getTypedAnswer();
        if (stories != null) {
            //AirportsTable.save(context, airports);
        }
    }
}
