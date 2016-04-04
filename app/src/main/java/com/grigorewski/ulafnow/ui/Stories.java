package com.grigorewski.ulafnow.ui;

import android.app.LoaderManager;
import android.content.Loader;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.grigorewski.ulafnow.R;
import com.grigorewski.ulafnow.api.response.Response;
import com.grigorewski.ulafnow.loaders.StoriesLoader;
import com.grigorewski.ulafnow.content.Story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stories extends Fragment
        implements LoaderManager.LoaderCallbacks<Response>{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stories, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(R.id.stories_loader, Bundle.EMPTY, this);
    }


    @Override
    public Loader<Response> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case R.id.stories_loader:
                return new StoriesLoader(getActivity());
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Response> loader, Response data) {
        int id = loader.getId();
        if (id == R.id.stories_loader) {
            List<Story> stories = data.getTypedAnswer();

            String[] from = {
                    "source",
                    "title"
            };

            int[] to = {
                    R.id.stories_list_item_source_textView,
                    R.id.stories_list_item_textView
            };

            final ArrayList<HashMap<String, Object>> dataMaps = new ArrayList<>(stories.size());
            for (Story story :
                    stories) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("source", story.getSource());
                map.put("title", story.getTitle());
                dataMaps.add(map);
            }

            SimpleAdapter adapter =
                    new SimpleAdapter(getActivity(), dataMaps, R.layout.stories_list_item, from, to);

            ((ListView) getActivity().findViewById(R.id.stories_list_view)).setAdapter(adapter);
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }

}
