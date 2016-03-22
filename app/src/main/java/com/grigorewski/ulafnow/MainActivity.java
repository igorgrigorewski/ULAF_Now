package com.grigorewski.ulafnow;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        FeedService.setListView((ListView)findViewById(R.id.listView));
        FeedService.ReadFromContent();
    }

    public static Context getContext(){
        return context;
    }
}
