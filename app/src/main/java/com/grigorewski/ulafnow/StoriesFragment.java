package com.grigorewski.ulafnow;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StoriesFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stories_fragment, container, false);
        FeedService.setListView((ListView) view.findViewById(R.id.stories_list_view));
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FeedService.setListView((ListView) getActivity().findViewById(R.id.stories_list_view));
        FeedService.ReadFromContent();
    }
}
