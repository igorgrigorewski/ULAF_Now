package com.grigorewski.ulafnow;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKBatchRequest;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPost;
import com.vk.sdk.api.model.VKAttachments;
import com.vk.sdk.api.model.VKPostArray;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ContentService {
    private static final int COUNT = 10;
    private static final int EXTENDED = 1;

    private static int OWNER_IDS[] = {
            -1118628, -116822349
    };

    public static ArrayList<HashMap<String, Object>> getData(){
        final ArrayList<HashMap<String, Object>> data;
        final ArrayList<String> listoftextposts = new ArrayList<>();

        VKPostArray posts = getPosts();
        Log.d("TAG", Integer.toString(posts.size()));
        posts = SortVkPostArrays(posts, 0, posts.size() - 1);

        for (int i = 0; i < posts.size(); i++) {
            listoftextposts.add(posts.get(i).text);
        }
        data = new ArrayList<>(listoftextposts.size());
        HashMap<String, Object> map;

        for (String str :
                listoftextposts) {
            map = new HashMap<>();
            map.put("Text", str);
            data.add(map);
        }

        return data;
    }

    private static VKPostArray SortVkPostArrays(VKPostArray vkPostArray, int start, int end){

        if (start >= end)
            return vkPostArray;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j){
            while (i < cur && (vkPostArray.get(i).date <= vkPostArray.get(cur).date))
                i++;
            while (j > cur &&  (vkPostArray.get(cur).date <= vkPostArray.get(j).date))
                j--;
            if (i < j){
                VKApiPost temp = vkPostArray.get(i);
                vkPostArray.set(i, vkPostArray.get(j));
                vkPostArray.set(j, temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }

        SortVkPostArrays(vkPostArray, start, cur);
        SortVkPostArrays(vkPostArray, cur + 1, end);

        return vkPostArray;
    }

    private static VKPostArray getPosts(){

        final VKPostArray posts = new VKPostArray();


        CreateBatch(OWNER_IDS).executeWithListener(new VKBatchRequest.VKBatchRequestListener() {
            @Override
            public void onComplete(VKResponse[] responses) {
                super.onComplete(responses);
                for (VKResponse response : responses) {
                    posts.addAll((VKPostArray) response.parsedModel);
                }
            }
        });
        /*CreateRequest(-1118628).executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                posts[0] = (VKPostArray) response.parsedModel;
            }
        });*/
        return posts;
    }

    private static VKRequest CreateRequest(int owner_id){
        return VKApi.wall().get(VKParameters.from(
                VKApiConst.OWNER_ID, owner_id/*-1118628*/,
                VKApiConst.COUNT, COUNT,
                "filter", "owner",
                VKApiConst.EXTENDED, EXTENDED
        ));
    }

    private static VKBatchRequest CreateBatch(int... owner_ids){
        VKRequest[] vkRequests = new VKRequest[owner_ids.length];
        for (int i = 0; i < owner_ids.length; i++){
            vkRequests[i] = CreateRequest(owner_ids[i]);
        }
        return new VKBatchRequest(vkRequests);
    }
}
