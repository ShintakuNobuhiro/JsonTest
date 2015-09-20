package com.example.shintaku.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ASyncGet asyncGet = new ASyncGet(new AsyncCallback() {
            public void onPreExecute() {
                // do something
            }
            public void onProgressUpdate(int progress) {
                // do something
            }
            public void onPostExecute(final String result) {
                // do something
                if (result != null)
                    Log.d("result", result);
                else
                    Log.d("result", "null");
                final Button btn = (Button) findViewById(R.id.button);
                final TextView textView = (TextView) findViewById(R.id.textView);
                final String[] description = new String[2];
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray missions = json.getJSONArray("missions");

                for (int i = 0; i < missions.length(); i++) {
                        JSONObject mission = missions.getJSONObject(i);
                        String category = mission.getString("category");
                        Log.d("", category);
                        description[i] = mission.getString("description");
                        String exp = mission.getString("exp");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText(description[0]);
                    }
                });
            }
            public void onCancelled() {
                // do something
            }
        });
        asyncGet.execute("https://railstutorial-ukyankyan-1.c9.io/users/1.json");
    }
}