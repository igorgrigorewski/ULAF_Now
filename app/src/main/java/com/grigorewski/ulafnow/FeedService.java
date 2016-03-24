package com.grigorewski.ulafnow;

import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class FeedService {

    private static ListView listView;

    public static void setListView(ListView lv){
        listView = lv;
    }
    public static void ReadFromContent(){
        ContentService.updateData();
    }

    public static void ReadFromContent(ArrayList<HashMap<String, Object>> data){
        setAdapter(data);
    }

    private static void setAdapter(ArrayList<HashMap<String, Object>> data){
        String[] from = {"groupName", "text"};
        int[] to = {R.id.list_item_group_name_textView, R.id.list_item_textView};

        try {
            SimpleAdapter adapter = new SimpleAdapter(MainActivityOld.getContext(), data, R.layout.list_item, from, to);
            listView.setAdapter(adapter);
        } catch (Exception ex){
            Log.d("ULAF_ERROR", "in ReadFromContent() error. Exception: " + ex);
        }
    }

}
