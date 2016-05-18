package com.grigorewski.ulafnow.ui.Stories;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grigorewski.ulafnow.R;

/**
 * @author Igor Grigorewski
 */
public class FullStory extends Fragment {

    private TextView title;
    private TextView content;

    public static FullStory getInstance(Bundle args){
        FullStory fragment = new FullStory();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_story, container, false);
        title = (TextView) view.findViewById(R.id.full_story_title);
        content = (TextView) view.findViewById(R.id.full_story_content);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        title.setText(getArguments()
                .getString("title"));
        content.setText(getArguments()
                .getString("content"));
    }
}
