package it.jaschke.alexandria;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.hardware.Camera;

public class ScannerActivity extends ActionBarActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    private String eanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.


        // NOTE: Explicitly selected device's main camera.
        // Leaving it blank didn't work on tablets without rear camera (frontface only)
        // NOTE: For versions less than API-21, Deprecated API - Camera has to be used,
        // NOTE: From API-21, Camera2 is available
        int numCameras = Camera.getNumberOfCameras();
        if (numCameras == 1) {
            mScannerView.startCamera(0);          // NOTE:Start camera on resume, stopped in pause
        }else{
            mScannerView.startCamera();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        //NOTE: Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        eanResult = rawResult.getText();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ScannerActivity.this);
        sp.edit().putString("EAN_RESULT",eanResult).commit();

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scanner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        //helps in going back to fragment.
        else if (item.getItemId() == android.R.id.home) {
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}