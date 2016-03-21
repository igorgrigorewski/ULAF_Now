package com.grigorewski.ulafnow;

import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class FeedService {

    private static ListView listView;

    public static void setListView(ListView lv){
        listView = lv;
    }
    public static void ReadFromConent(){
        //if (!listView.equals(null)) {
        ArrayList<HashMap<String, Object>> data = ContentService.getData();

        String[] from = {"Text"};
        int[] to = {R.id.list_item_textView};

        SimpleAdapter adapter = new SimpleAdapter(MainActivity.getContext(), data, R.layout.list_item, from, to);
        listView.setAdapter(adapter);
        //}
    }

    public static void WriteInContent(){

    }
}
