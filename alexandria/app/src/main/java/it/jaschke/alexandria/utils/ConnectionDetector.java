package it.jaschke.alexandria.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kchandra on 12.06.15.
 */
public class ConnectionDetector {

    private Context context;
    //cant be instantiated as its an Util class
    public ConnectionDetector(Context context){
        this.context = context;
    }



    public boolean isNetConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
}
