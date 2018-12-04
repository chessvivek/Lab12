package edu.illinois.cs.cs125.lab12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.HashMap;
import java.util.Map;
import java.lang.Object;
import java.util.concurrent.TimeUnit;

import com.cloudmersive.client.*;
import com.cloudmersive.client.model.*;
import com.cloudmersive.client.invoker.*;
import com.cloudmersive.client.invoker.auth.*;
import java.io.*;


import org.json.JSONObject;

import java.lang.String;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

/**
 * Main screen for our API testing app.
 */
public final class MainActivity extends AppCompatActivity {
    /** Default logging tag for messages from the main activity. */
    private static final String TAG = "Testing:";

    /** Request queue for our network requests. */
    private static RequestQueue requestQueue;

//    ApiClient client = new ApiClient();
 //   client.setHeaders("Apikey", "a3be09fc-e90b-4ff5-b0f1-94682b613a86");

    /**
     * Run when our activity comes into view.
     *
     * @param savedInstanceState state that was saved by the activity last time it was paused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up a queue for our Volley requests
        requestQueue = Volley.newRequestQueue(this);

        // Load the main layout for our activity
        setContentView(R.layout.activity_main);

        // Attach the handler to our UI button
        final Button startAPICall = findViewById(R.id.startAPICall);
        startAPICall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Log.d(TAG, "Start API button clicked");
                startAPICall();
    //            startAPICall2();
            }
        });

        // Make sure that our progress bar isn't spinning and style it a bit
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    /**
     * Make an API call.
     */

    void startAPICall() {
        try {
            ApiClient defaultClient = Configuration.getDefaultApiClient();
            ApiKeyAuth Apikey = (ApiKeyAuth) defaultClient.getAuthentication("Apikey");
            Apikey.setApiKey("a3be09fc-e90b-4ff5-b0f1-94682b613a86");

            ConvertDocumentApi apiInstance = new ConvertDocumentApi();
            File inputFile = new File("C:\\Users\\VIVEKG~1\\AppData\\Local\\Temp\\output-3478612807235997346.pdf"); // File | Input file to perform the operation on.
            try {
                System.out.println(inputFile);
                Object result = apiInstance.convertDocumentPdfToPngArray(inputFile);
                System.out.println(result);
            } catch (ApiException e) {
                System.out.println(e);
                System.err.println("Exception when calling ConvertDocumentApi#convertDocumentDocxToPdf");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.toString() + e.getMessage() );
        }
    }


/*
    void startAPICall2() {
        if(b)
                return;
        String url2 = "https://api.darksky.net/forecast/";
        String key2 = "22c13de942ce24d4a51f8a9be9adb21b";
        String finurl2 = url2 + key2 + "/" + lat + "," + lon;
        final JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                Request.Method.GET, finurl2,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.w(TAG, error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest2);
    }
    */

}
