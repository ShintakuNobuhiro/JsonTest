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
                Log.d("onPostExecute", result);
                final Button btn = (Button) findViewById(R.id.button);
                final TextView textView = (TextView) findViewById(R.id.textView);
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray missions = json.getJSONArray("missions");
                    String description = null;
                    for (int i = 0; i < missions.length(); i++) {
                        JSONObject mission = missions.getJSONObject(i);
                        String category = mission.getString("category");
                        description = mission.getString("description");
                        String exp = mission.getString("exp");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView.setText(result);
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