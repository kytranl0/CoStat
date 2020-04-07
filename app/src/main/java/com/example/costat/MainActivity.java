package com.example.costat;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
//    LineGraphSeries<DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button button = (Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondActivity();
            }
        });

        double y,x;
        x=-5.0;

//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        series = new LineGraphSeries<DataPoint>();
//        for(int i = 0; i<500; i++) {
//            x = x + 0.1;
//            y = Math.sin(x);
//            series.appendData(new DataPoint(x,y), true, 500);
//        }
//        graph.addSeries(series);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    private void goToSecondActivity() {
        Log.d("stuff", "hello");
        OkHttpHandler handler = new OkHttpHandler();
        try {
            handler.execute("yeet").get();
        } catch (Exception e) {

        }

//        Intent intent = new Intent(this, SecondActivity.class);
//        startActivity(intent);
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
    public void jsonToMap(String t) throws JSONException {

        HashMap<String, String> map = new HashMap<String, String>();
        JSONObject jObject = new JSONObject(t);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, value);

        }

        TextView textView = (TextView) findViewById(R.id.textView);

        TextView death = (TextView) findViewById(R.id.textView5);

        TextView recovered = (TextView) findViewById(R.id.textView6);

        textView.setText(map.get("cases"));
        death.setText(map.get("deaths"));
        recovered.setText(map.get("recovered"));

    }
}
