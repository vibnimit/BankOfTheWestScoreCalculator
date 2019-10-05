package com.example.vibhu.bankofthewestscorecalculator;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by suhasbachewar on 10/5/16.
 */
public class SendGetRequest extends AsyncTask {

    private static final String TAG = SendGetRequest.class.getName();

    String url = "https://api.ocr.space/parse/image"; // OCR API Endpoints

    private String mApiKey;
    private boolean isOverlayRequired = false;
    private String mImageUrl;
    private String mLanguage;
    private Activity mActivity;
    private ProgressDialog mProgressDialog;
    private IOCRCallBack mIOCRCallBack;

    public SendGetRequest(Activity activity, String apiKey, boolean isOverlayRequired, String byte64Image, String language) {
        this.mActivity = activity;
        this.mApiKey = apiKey;
        this.isOverlayRequired = isOverlayRequired;
        this.mImageUrl = byte64Image;
        this.mLanguage = language;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setTitle("Wait while processing....");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object[] params) {

        try {
//            return sendPost(mApiKey, isOverlayRequired, mImageUrl, mLanguage);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//    private String sendPost(String apiKey, boolean isOverlayRequired, String byte64Image, String language) throws Exception {
//
//        URL obj = new URL(url); // OCR API Endpoints
//        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
//
//        //add request header
//        con.setRequestMethod("GET");
//        con.setRequestProperty("User-Agent", "Mozilla/5.0");
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//
//
//        JSONObject postDataParams = new JSONObject();
//
//        postDataParams.put("apikey", apiKey);//TODO Add your Registered API key
//        postDataParams.put("isOverlayRequired", isOverlayRequired);
//        postDataParams.put("base64Image", byte64Image);
//        postDataParams.put("language", language);
//
//
//        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(getPostDataString(postDataParams));
//        wr.flush();
//        wr.close();
//
//        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//        String inputLine;
//        StringBuffer response = new StringBuffer();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
//        in.close();
//
//        //return result
//        return String.valueOf(response);
//    }
//
//    @Override
//    protected void onPostExecute(Object result) {
//        super.onPostExecute(result);
//        if (mProgressDialog != null && mProgressDialog.isShowing())
//            mProgressDialog.dismiss();
//        String response = (String) result;
////        mIOCRCallBack.getOCRCallBackResult(response);
//        System.out.print("response =   "+response);
//        Log.d(TAG, response.toString());
//    }
//
//    public String getPostDataString(JSONObject params) throws Exception {
//
//        StringBuilder result = new StringBuilder();
//        boolean first = true;
//
//        Iterator<String> itr = params.keys();
//
//        while (itr.hasNext()) {
//
//            String key = itr.next();
//            Object value = params.get(key);
//
//            if (first)
//                first = false;
//            else
//                result.append("&");
//
//            result.append(URLEncoder.encode(key, "UTF-8"));
//            result.append("=");
//            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
//
//        }
//        return result.toString();
//    }
}


