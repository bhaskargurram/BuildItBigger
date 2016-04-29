package com.udacity.gradle.builditbigger;


import android.widget.Toast;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;

import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainActivityFragment extends Fragment {

    InterstitialAd mInterstitialAd;
    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root =  inflater.inflate(R.layout.fragment_main_activity, container, false);

              AdView mAdView = (AdView) root.findViewById(R.id.adView);
              // Create an ad request. Check logcat output for the hashed device ID to
              // get test ads on a physical device. e.g.
              // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
              AdRequest adRequest = new AdRequest.Builder()
                      .addTestDevice("D9C53D9830C107019E24839C5FAD9637")
                      .build();
              mAdView.loadAd(adRequest);

                      mInterstitialAd = new InterstitialAd(getActivity());

                      // set the ad unit ID
                      mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                                  mInterstitialAd.loadAd(adRequest);

                      mInterstitialAd.setAdListener(new AdListener() {
                          @Override
                          public void onAdClosed() {
                              requestNewInterstitial();
                              //Toast.makeText(getActivity(), "Ad closed" , Toast.LENGTH_SHORT).show();
                              new EndpointsAsyncTask(getActivity(), false).execute("Bhaskar");

                          }
                          @Override
                          public void onAdFailedToLoad(int errorCode) {
                              Toast.makeText(getActivity(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
                          }
                      });
  Button btn = (Button) root.findViewById(R.id.tellJoke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tellJoke(v);
            }
        });

              return root;
    }

    public void tellJoke(View view) {
        showInterstitial();

        /*
        if (mInterstitialAd.isLoaded()) {
            showInterstitial();
            // new EndpointsAsyncTask(this, false).execute("Bhaskar");
        } else {
            new EndpointsAsyncTask(this, false).execute("Bhaskar");
            //beginPlayingGame();
        }
*/

    }
        private void showInterstitial() {

            requestNewInterstitial();
            mInterstitialAd.show();

        }

        private void requestNewInterstitial() {

            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice("D9C53D9830C107019E24839C5FAD9637")
                    .build();
            mInterstitialAd.loadAd(adRequest);
        }



}
