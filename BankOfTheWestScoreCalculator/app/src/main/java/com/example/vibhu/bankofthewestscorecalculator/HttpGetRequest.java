package com.example.vibhu.bankofthewestscorecalculator;

import android.content.Intent;
import android.os.AsyncTask;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class HttpGetRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    private ProgressDialog mProgressDialog;
    private Activity mActivity;

    public HttpGetRequest(Activity activity){
        this.mActivity = activity;
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
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result = "";
        try{
            result = getJSONObjectFromURL(stringUrl);
        }
        catch (Exception e){
            e.printStackTrace();
        }

//        System.out.print("@@@@@@@@ "+stringUrl);
//        String result;
//        String inputLine;
//        try {
//            //Create a URL object holding our url
//            URL myUrl = new URL(stringUrl);         //Create a connection
//            HttpURLConnection connection =(HttpURLConnection)myUrl.openConnection();         //Set methods and timeouts
//            connection.setRequestMethod(REQUEST_METHOD);
//            connection.setReadTimeout(READ_TIMEOUT);
//            connection.setConnectTimeout(CONNECTION_TIMEOUT);
//
//            //Connect to our url
//            connection.connect();        //Create a new InputStreamReader
//            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());         //Create a new buffered reader and String Builder
//            BufferedReader reader = new BufferedReader(streamReader);
//            StringBuilder stringBuilder = new StringBuilder();         //Check if the line we are reading is not null
//            while((inputLine = reader.readLine()) != null){
//                stringBuilder.append(inputLine);
//            }         //Close our InputStream and Buffered reader
//            reader.close();
//            streamReader.close();         //Set our result equal to our stringBuilder
//            result = stringBuilder.toString();
//        }
//        catch(IOException e){
//
////            e.printStackTrace();
//            result = null;
//        }

        return result;
    }

    public static String getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return jsonString;
    }

    protected void onPostExecute(String result){
            super.onPostExecute(result);
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();

        System.out.print("rs =   "+result);
        }

}