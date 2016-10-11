package it.jaschke.alexandria;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class About extends Fragment {

    public About(){

    }

   //BUG_FIX: Action title bar getting removed from 
   //"Scan/Add a book" to Alexandria on Device rotation/Screen lock
    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.about);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        activity.setTitle(R.string.about);
    }

}
