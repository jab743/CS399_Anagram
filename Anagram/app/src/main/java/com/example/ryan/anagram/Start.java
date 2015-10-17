package com.example.ryan.anagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Start extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TextView anagram = (TextView) findViewById(R.id.anagram);
        anagram.setText(generateAnagram("TEST"));
        anagram.setTextSize(72);
    }

    /*
    public void buttonResultsClicked(View v) {
        startActivity(new Intent(getApplicationContext(), Results.class));
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
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

    private String generateAnagram(String input) {
        ArrayList<Character> anagram = new ArrayList<>();
        for(char letter:input.toLowerCase().toCharArray()) {
            anagram.add(letter);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(anagram.size()!=0){
            int rand = (int)(Math.random() * anagram.size());
            output.append(anagram.remove(rand));
        }
        return output.toString().toUpperCase();
    }
}
