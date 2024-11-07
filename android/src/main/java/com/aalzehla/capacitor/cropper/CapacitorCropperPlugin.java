package com.aalzehla.capacitor.cropper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.activity.result.ActivityResult;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Objects;

@CapacitorPlugin(name = "CapacitorCropper")
public class CapacitorCropperPlugin extends Plugin {

    private final CapacitorCropper implementation = new CapacitorCropper();
    private PluginCall savedCall;


    @PluginMethod
    public void crop(PluginCall call) {
        Log.i("CapacitorCropperPlugin", "crop called");
        savedCall = call;
        if (savedCall.getData() == null) {
            savedCall.reject("Image pick failed");
            return;
        }
        Uri sourceUri = savedCall.getString("uri") != null ? Uri.parse(savedCall.getString("uri")) : null;

        Integer xValue = savedCall.getInt("x");
        int x = (xValue != null) ? xValue : 0;

        Integer yValue = savedCall.getInt("y");
        int y = (yValue != null) ? yValue : 0;

        if (sourceUri == null) {
            savedCall.reject("Image pick failed");
            return;
        }
        Uri destinationUri = Uri.fromFile(new File(getContext().getCacheDir(), "cropped_image.png"));


        UCrop.Options options = new UCrop.Options();
        options.withMaxResultSize(800, 800);
        if (x != 0 || y != 0) {
            options.withAspectRatio(x, y);
        }


        UCrop uCrop = UCrop.of(sourceUri, destinationUri)
                .withOptions(options);

        Intent intent = uCrop.getIntent(getContext());
        startActivityForResult(savedCall, intent, "handleCropResult");

    }


    @ActivityCallback
    public void handleCropResult(PluginCall call, ActivityResult result) {
        Log.i("CapacitorCropperPlugin", "handleCropResult called");
        if (call == null) {
            savedCall.reject("Image crop failed");
            return;
        }
        if (result != null && UCrop.getOutput(Objects.requireNonNull(result.getData())) != null) {
            Uri croppedUri = UCrop.getOutput(result.getData());
            String base64Image = convertUriToBase64(croppedUri);

            JSObject ret = new JSObject();
            String base64 = "data:image/png;base64," + base64Image;
            ret.put("result", base64);
            savedCall.resolve(ret);
        } else {
            savedCall.reject("Image crop failed");
        }
    }

    private String convertUriToBase64(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            savedCall.reject("Failed to convert image to Base64", e);
            return null;
        }
    }
}
