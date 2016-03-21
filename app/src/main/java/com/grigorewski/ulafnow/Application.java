package com.grigorewski.ulafnow;

import android.content.Context;

import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
