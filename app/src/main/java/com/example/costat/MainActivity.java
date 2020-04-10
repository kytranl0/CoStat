package com.example.costat;

import android.content.Intent;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView) findViewById(R.id.listview);

        ArrayList<String> arrList = new ArrayList<>();

        arrList.add("c");
        arrList.add("a");
        arrList.add("b");
        arrList.add("c");
        arrList.add("a");
        arrList.add("b");
        arrList.add("c");
        arrList.add("a");
        arrList.add("b");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, arrList);
        listView.setAdapter(arrayAdapter);


        Button button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSecondPage();
            }
        });

//        double y,x;
//        x=-5.0;

//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        series = new LineGraphSeries<DataPoint>();
//        for(int i = 0; i<500; i++) {
//            x = x + 0.1;
//            y = Math.sin(x);
//            series.appendData(new DataPoint(x,y), true, 500);
//        }
//        graph.addSeries(series);


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }



    private void goToSecondPage() {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }





}
