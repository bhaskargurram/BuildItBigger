package com.udacity.gradle.builditbigger;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class MainActivityFragment extends Fragment {


    public MainActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View root =  inflater.inflate(R.layout.fragment_main, container, false);

         Button btn = (Button) root.findViewById(R.id.tellJoke);
                 btn.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                        new EndpointsAsyncTask(getActivity(), false).execute("Bhaskar");
                     }
                 });

              return root;
    }

}
