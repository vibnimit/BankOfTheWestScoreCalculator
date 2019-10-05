package com.example.vibhu.bankofthewestscorecalculator;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.support.constraint.Constraints.TAG;


public class CameraActivity extends Activity {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    private Camera mCamera;
    private CameraPreview mPreview;
    private static final int CAMERA_REQUEST_PERMISSIONS_REQUEST_CODE = 28;
    private static final int STORAGE_REQUEST_PERMISSIONS_REQUEST_CODE = 24;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_fragment);
//        Toast.makeText(this, "Please provideD the permission", Toast.LENGTH_LONG).show();

        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, CAMERA_REQUEST_PERMISSIONS_REQUEST_CODE);
        }
        else {
            // Create an instance of Camera
            startCamera();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_REQUEST_PERMISSIONS_REQUEST_CODE)
            startCamera();
//        Toast.makeText(getApplicationContext()," *********************** "+permissions ,Toast.LENGTH_LONG).show();

    }

    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "DineIn");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ Direc  :"+ mediaStorageDir);
//        Toast.makeText(.(), "Directory", Toast.LENGTH_LONG).show();
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@ File  :"+ mediaFile);
        return mediaFile;
    }

    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//            Toast.makeText(this, "Take", Toast.LENGTH_LONG).show();
            System.out.print("@@@@@@@@@@@@@@@@@@@@@@ Taken");
            if (pictureFile == null){
                Log.d(TAG, "Error creating media file, check storage permissions");
                return;
            }

            try {
                FileOutputStream fos = new FileOutputStream(pictureFile);
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e) {
                Log.d(TAG, "File not found: " + e.getMessage());
            } catch (IOException e) {
                Log.d(TAG, "Error accessing file: " + e.getMessage());
            }


            String encodeImageString = getByte64String(pictureFile);
//            SendGetRequest oCRAsyncTask = new SendGetRequest(CameraActivity.this, "083b32e82a88957", true, encodeImageString, "eng");
//            oCRAsyncTask.execute();
            //Some url endpoint that you may have
            String myUrl = "https://api.ocr.space/parse/imageurl?apikey=083b32e82a88957&url=https://agape.com.co/wp-content/uploads/2019/01/can-i-pay-my-target-bill-with-a-gift-card-target-red-card-lost-receipt-pay-target-bill-with-gift-card-can-you-pay-your-target-red-card-bill-with-a-target-gift-card.jpg";
                String url = "https://reqres.in/api/users?page=2";

            HttpGetRequest getRequest = new HttpGetRequest(CameraActivity.this);   //Perform the doInBackground method, passing in our url
            try {
                getRequest.execute(myUrl);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            final Intent intent = new Intent(CameraActivity.this, ScoreActivity.class);
            startActivity(intent);

// String result;   //Instantiate new instance of our class
//            HttpGetRequest getRequest = new HttpGetRequest();   //Perform the doInBackground method, passing in our url


//
//            final Intent intent = new Intent(CameraActivity.this, ResultActivity.class);
//            startActivity(intent);

            System.out.print("@@@@@@@@@@@@@@@@@@@@@@ Ended");
        }
    };


    private String getByte64String(File imageFile){
        Bitmap bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bm.compress(Bitmap.CompressFormat.JPEG, 0, baos); //bm is the bitmap object
        }catch(Exception e){
            e.printStackTrace();
        }
        byte[] byteArrayImage = baos.toByteArray();
        String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
        return encodedImage;
    }


    private void startCamera(){
        mCamera = getCameraInstance();
        Camera.Parameters parameters;
        parameters = mCamera.getParameters();
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        mCamera.setParameters(parameters);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);

        Button captureButton = (Button) findViewById(R.id.button_capture);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get an image from the camera
                mCamera.takePicture(null, null, mPicture);
            }
        });
    }



    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


}
