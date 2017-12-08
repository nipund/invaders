package me.dayanath.game;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HighScoresActivity extends ListActivity {

    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        /*db = new DatabaseHandler(this);

        ArrayList<String> listValues = new ArrayList<String>();
        HashMap<String, Integer> map = db.getAllScores();

        for(Map.Entry<String, Integer> e: map.entrySet()) {
            listValues.add(e.getKey() + ": " + e.getValue());
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(this,
                R.layout.row_layout, R.id.listText, listValues);

        setListAdapter(myAdapter);*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        db = new DatabaseHandler(this);

        ArrayList<String> listValues = new ArrayList<String>();
        HashMap<String, Integer> map = db.getAllScores();

        Log.d("Hash", ""+map.size());

        for (Map.Entry<String, Integer> e : map.entrySet()) {
            listValues.add(e.getKey() + ": " + e.getValue());
        }

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,
                R.layout.row_layout, R.id.listText, listValues);

        setListAdapter(myAdapter);
    }

}
