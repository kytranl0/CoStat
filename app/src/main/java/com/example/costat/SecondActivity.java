package com.example.costat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeGetCall();
            }
        });

        Button button3 = (Button)findViewById(R.id.button3);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFirstPage();
            }
        });

    }

    public void updateText(HashMap<String, String> map) {
        TextView textView = (TextView) findViewById(R.id.textView);

        TextView death = (TextView) findViewById(R.id.textView5);

        TextView recovered = (TextView) findViewById(R.id.textView6);

        textView.setText(map.get("cases"));
        death.setText(map.get("deaths"));
        recovered.setText(map.get("recovered"));
    }


    public void executeGetCall() {
        Log.d("stuff", "hello");
        SecondActivity.OkHttpHandler handler = new SecondActivity.OkHttpHandler();
        try {
            handler.execute("yeet").get();
        } catch (Exception e) {

        }

    }

    private class OkHttpHandler extends AsyncTask<String, Void, byte[]> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected byte[] doInBackground(String... params) {
            Request request = new Request.Builder()
                    .url("https://coronavirus-19-api.herokuapp.com/all")
                    .build();
            try {
                Log.d("stuff", "sup");
                Response response = client.newCall(request).execute();
                Log.d("stuff", String.valueOf(response.isSuccessful()));
                jsonToMap(response.body().string());
                return response.body().bytes();
            } catch (Exception e) {
                Log.d("stuff", e.toString());
            }
            return null;
        }


    }

    public void jsonToMap(String t) {
        Log.d("gat", "onPostExecute");
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            JSONObject jObject = new JSONObject(t);
            Iterator<?> keys = jObject.keys();

            while( keys.hasNext() ){

                String key = (String)keys.next();
                String value = jObject.getString(key);
                map.put(key, value);
            }
            Log.d("gat", map.get("deaths"));

        } catch(JSONException e) {

        }

        updateText(map);

    }
    private void goToFirstPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
