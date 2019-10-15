package io.github.normansyarif.jobscheculer;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class LocationService extends Service {
    private static final String TAG = "LocationService";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;

    public LocationService(Context ctx) {
        this.context = ctx;
        requestPermissions();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.context);
        requestLocation();
    }

    private void requestPermissions() {

        ActivityCompat.requestPermissions((Activity) this.context, new String[] {ACCESS_FINE_LOCATION}, 1);
    }

    private void requestLocation() {
        if (ActivityCompat.checkSelfPermission(this.context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener((Activity) this.context, new OnSuccessListener<Location>() {

            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    Toast.makeText(context, location.toString(), Toast.LENGTH_SHORT).show();
                    new HttpConn(getApplication()).execute("gps location", location.toString() + "");
                }else{
                    Log.d(TAG, "Getting location failed");
                }
            }
        });
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
