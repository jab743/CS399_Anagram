package com.example.ryan.anagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ryan.anagram.Start;

public class Results extends Activity {
    TextView textScore;
    int score = Start.getScore();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        textScore = (TextView) findViewById(R.id.textScore);
        textScore.setText(Integer.toString(score));
    }

    public void buttonStartClicked(View v) {
        startActivity(new Intent(getApplicationContext(), Start.class));
    }

    public void buttonHomeClicked(View v) {
        startActivity(new Intent(getApplicationContext(), Anagram.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);
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
}
