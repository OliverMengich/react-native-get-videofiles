package com.getvideofiles;

import androidx.annotation.NonNull;

import android.content.ContentResolver;
import com.facebook.react.bridge.Promise;
import android.content.pm.PackageManager;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import androidx.core.content.ContextCompat;
import android.content.Context;
import com.facebook.react.bridge.WritableNativeMap;
import android.database.Cursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.react.bridge.ReadableMap;
import android.provider.MediaStore;

@ReactModule(name = GetVideofilesModule.NAME)
public class GetVideofilesModule extends ReactContextBaseJavaModule {
    public static final String NAME = "GetVideofiles";

    public GetVideofilesModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }
    private boolean hasPermissions() {
        return ContextCompat.checkSelfPermission(getReactApplicationContext(), android.Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void getAll(ReadableMap options,final Promise promise) {
        ContentResolver musicResolver = getCurrentActivity().getContentResolver();
        if (!hasPermissions()) {
            promise.reject("Permissions denied", "Permissions denied");
            return;
        }

        String[] projection = {
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.DATA
        };
        

        Cursor cursor = musicResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        );

        if (cursor != null) {
            JSONArray videoArray = new JSONArray();
            while (cursor.moveToNext()) {
                JSONObject videoObject = new JSONObject();
                try {
                    videoObject.put("id", cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media._ID)));
                    videoObject.put("displayName", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME)));
                    videoObject.put("duration", cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)));
                    videoObject.put("size", cursor.getLong(cursor.getColumnIndex(MediaStore.Video.Media.SIZE)));
                    videoObject.put("resolution", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.RESOLUTION)));
                    videoObject.put("path", cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)));
                    videoArray.put(videoObject);
                } catch (JSONException e) {
                    promise.reject("Error", "Error parsing video info");
                }
            }

            cursor.close();

            // Send the video list to React Native side
            promise.resolve(videoArray.toString());
        } else {
            cursor.close(); // Close the cursor even if it's empty
            promise.reject("Error", "Cursor is empty");
        }
    }
}
