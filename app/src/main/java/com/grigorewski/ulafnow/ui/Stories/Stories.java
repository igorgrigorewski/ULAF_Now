package com.grigorewski.ulafnow.ui.Stories;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.grigorewski.ulafnow.R;
import com.grigorewski.ulafnow.api.response.Response;
import com.grigorewski.ulafnow.content.Story;
import com.grigorewski.ulafnow.loaders.StoriesLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stories extends ListFragment
        implements LoaderManager.LoaderCallbacks<Response>{

    List<Story> stories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
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
            stories = data.getTypedAnswer();

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

            setListAdapter(adapter);
        }
        getLoaderManager().destroyLoader(id);
    }

    @Override
    public void onLoaderReset(Loader<Response> loader) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Bundle bundle = new Bundle();
        bundle.putString("source", stories.get(position).getSource());
        bundle.putString("title", stories.get(position).getTitle());
        bundle.putString("content", stories.get(position).getContent());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main_container, FullStory.getInstance(bundle))
                .commit();

    }


}