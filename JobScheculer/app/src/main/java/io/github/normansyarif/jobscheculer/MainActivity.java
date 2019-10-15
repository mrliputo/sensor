package io.github.normansyarif.jobscheculer;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    private static final int JOB_ID = 123;
    private static final String TAG = "MainActivity";
    BroadcastReceiver broadcastReceiver;
    TextView tv;
    Button btn_start, btn_stop;

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    tv.append("\n" + intent.getExtras().get("coordinates"));
                }
            };
        }

        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.coordinates);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        
        if(!runtimePermission()) {
            enableButtons();
        }
    }

    private void enableButtons() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GpsService.class);
                startService(i);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GpsService.class);
                stopService(i);
            }
        });
    }

    private boolean runtimePermission() {
        if(Build.VERSION.SDK_INT >= 23
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
            return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                enableButtons();
            }else{
                runtimePermission();
            }
        }
    }

//    public void scheduleJob(View v) {
//        ComponentName componentName = new ComponentName(this, ExampleService.class);
//
//        PersistableBundle bundle = new PersistableBundle();
//        bundle.putString("username", "oi");
//        bundle.putString("password", "nani shiteruyo");
//
//        JobInfo info = new JobInfo.Builder(JOB_ID, componentName)
//                .setExtras(bundle)
//                .setRequiresCharging(false)
//                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//                .setPersisted(true)
//                .setPeriodic(15 * 60 * 1000) //15 minutes
//                .build();
//
//        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
//        int resultCode = scheduler.schedule(info);
//
//        if(resultCode == JobScheduler.RESULT_SUCCESS) {
//            Log.d(TAG, "Job scheduled");
//        }else{
//            Log.d(TAG, "Job scheduling failed");
//        }
//    }

//    public void cancelJob(View v) {
////        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
////        scheduler.cancel(JOB_ID);
////        Log.d(TAG, "Job cancelled");
//        LocationService locationService = new LocationService(this);
//        Toast.makeText(this, locationService.getLatitude() + ", " + locationService.getLongitude(), Toast.LENGTH_SHORT).show();
//    }
}
