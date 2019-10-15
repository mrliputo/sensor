package io.github.normansyarif.jobscheculer;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ExampleService extends JobService {
    public static final String TAG = "ExampleService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "job started");
//        String username = jobParameters.getExtras().getString("username");
//        String password = jobParameters.getExtras().getString("password");
//        new HttpConnection(this, username, password).execute(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        return false;
    }
}
