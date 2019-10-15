package io.github.normansyarif.jobscheculer;

import android.app.AlertDialog;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpConnection extends AsyncTask<JobParameters, Void, JobParameters> {
    private final JobService jobService;
    private String username, password, result;

    HttpConnection(JobService jobService, String username, String password) {
        this.jobService = jobService;
        this.username = username;
        this.password = password;
    }

    @Override
    protected JobParameters doInBackground(JobParameters... params) {

        try {
            String username = this.username;
            String password = this.password;
            Log.d("HttpConnection", password);
            URL url = new URL("http://192.168.43.249:81/sensor/web/insert.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String postData = URLEncoder.encode("user_name", "UTF-8")+"="+URLEncoder.encode(username, "UTF-8")+"&"
                    +URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(password, "UTF-8");
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            StringBuilder result = new StringBuilder();
            String line = "";

            while((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            this.result = result.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return params[0];
    }

    @Override
    protected void onPostExecute(JobParameters jobParameters) {
        Toast.makeText(jobService, this.result, Toast.LENGTH_SHORT).show();
        Log.d("HttpConnection", "Done done done");
        jobService.jobFinished(jobParameters, false);
    }

}
