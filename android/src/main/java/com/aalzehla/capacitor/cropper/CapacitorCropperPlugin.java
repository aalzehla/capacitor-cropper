package com.aalzehla.capacitor.cropper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.ActivityCallback;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;

@CapacitorPlugin(name = "CapacitorCropper")
public class CapacitorCropperPlugin extends Plugin {

    private final CapacitorCropper implementation = new CapacitorCropper();
    private PluginCall savedCall;


    @PluginMethod
    public void crop(PluginCall call) {
        savedCall = call;
        if (savedCall.getData() == null) {
            savedCall.reject("Image pick failed");
            return;
        }
        Uri sourceUri = call.getString("uri") != null ? Uri.parse(call.getString("uri")) : null;
        if (sourceUri == null) {
            savedCall.reject("Image pick failed");
            return;
        }
        Uri destinationUri = Uri.fromFile(new File(getContext().getCacheDir(), "cropped_image.jpg"));


        UCrop.of(sourceUri, destinationUri)
                .withAspectRatio(16, 9)
                .withMaxResultSize(800, 800)
                .start(getActivity());

    }


    @ActivityCallback
    public void handleCropResult(Intent data) {
        if (data != null && UCrop.getOutput(data) != null) {
            Uri croppedUri = UCrop.getOutput(data);
            String base64Image = convertUriToBase64(croppedUri);

            JSObject ret = new JSObject();
            ret.put("result", implementation.echo(base64Image));
            savedCall.resolve(ret);
        } else {
            savedCall.reject("Image crop failed");
        }
    }

    private String convertUriToBase64(Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            byte[] byteArray = outputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        } catch (Exception e) {
            savedCall.reject("Failed to convert image to Base64", e);
            return null;
        }
    }
}
