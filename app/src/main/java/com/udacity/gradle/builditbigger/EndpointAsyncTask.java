package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.bhaskar.andlibrary.DisplayJoke;
import com.example.bhaskar.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;
    ProgressDialog dialog;
    Context mContext;
    boolean test;

    EndpointsAsyncTask(Context context, boolean test) {

        mContext = context;
        this.test = test;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!test) {
            dialog = new ProgressDialog(mContext);
            dialog.setMessage("Fetching Joke");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://udacity-1251.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }


        String name = params[0];
        Log.d("bhaskar", name);
        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
        if (!test)
            dialog.dismiss();


        if (!result.equals("timeout")) {
            Intent intent = new Intent(mContext, DisplayJoke.class);
            intent.putExtra("joke", result);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            ((Activity) mContext).startActivityForResult(intent, 0);
        } else {
            Toast.makeText(mContext, "Timeout", Toast.LENGTH_LONG).show();

        }
    }
}