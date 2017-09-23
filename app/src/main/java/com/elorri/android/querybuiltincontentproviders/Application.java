package com.elorri.android.querybuiltincontentproviders;

import com.facebook.stetho.Stetho;

/**
 * Created by Elorri on 23/09/2017.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
