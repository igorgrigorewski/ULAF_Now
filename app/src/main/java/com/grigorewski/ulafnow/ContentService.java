package com.grigorewski.ulafnow;

import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKPostArray;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ContentService {
    private List<Post> postList;

    public static ArrayList<HashMap<String, Object>> getData(){
        final ArrayList<HashMap<String, Object>> data;
        final ArrayList<String> listoftextposts = new ArrayList<>();

        VKPostArray posts = getPosts();
        for (int i = 0; i < posts.size(); i++) {
            listoftextposts.add(posts.get(i).text);
        }
        data = new ArrayList<>(listoftextposts.size());
        HashMap<String, Object> map;

        for (String str :
                listoftextposts) {
            map = new HashMap<>();
            /*if (str.length() > 57)
                str = str.substring(0,57) + "...";*/
            map.put("Text", str);
            data.add(map);
        }

        /*for (int i = 0; i < listoftextposts.size(); i++){
            map = new HashMap<>();
            map.put("Text", posts.get(i).text);
            VKAttachments attachments = posts.get(i).attachments;
            if (attachments.get(0).getType() == "posted_photo"){
                map.put("Image", attachments.get(0));
            }
            data.add(map);
        }*/

        return data;
    }



    public static VKPostArray getPosts(){
        VKRequest request = VKApi.wall().get(VKParameters.from(
                VKApiConst.OWNER_ID, -1118628,
                VKApiConst.COUNT, 10,
                "filter", "owner",
                VKApiConst.EXTENDED, 1
        ));

        final VKPostArray[] posts = {new VKPostArray()};

        request.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                posts[0] = (VKPostArray) response.parsedModel;
            }
        });
        return posts[0];
    }
}
