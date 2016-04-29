package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by bhaskar on 28/4/16.
 */
public class NonEmptyString extends AndroidTestCase {


    public void test() {
        EndpointsAsyncTask endpoint = new EndpointsAsyncTask(getContext(), true);
        endpoint.execute("bhaskar");
        try {
            String result = endpoint.get();
            Log.v("androidTestCase", result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
