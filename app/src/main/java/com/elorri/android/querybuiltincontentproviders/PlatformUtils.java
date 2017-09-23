package com.elorri.android.querybuiltincontentproviders;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PlatformUtils {

    // Request code for WRITE_CONTACTS. It can be any number > 0.
    public static final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 100;

    public static void checkAndAskForPermission(Activity activity, String permission) {
        //Before Marshmallow api 23 all permissions were requested at app first installed, asking permission dynamically wasn't possible
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        //The user has already granted the permission we need. We can move on.
        if (ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //The user has not granted the permission we need. We ask him to do so.
        ActivityCompat.requestPermissions(activity, new String[]{permission}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
        //activity.requestPermissions(new String[]{permission}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
    }

}
