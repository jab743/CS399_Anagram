package com.example.ryan.anagram;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class Start extends Activity {

    String word;
    TextView roundCounter;
    EditText answer;
    TextView anagram;
    Button enterGuess;
    static int score=0;
    ArrayList<String> words;
    int currentRound = 1;
    int totalRounds = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        try {
            initializeWords(this);
        } catch (Exception e) {
            words = new ArrayList<>();
            System.out.println(e);
        }

        word = getWord();
        roundCounter = (TextView) findViewById(R.id.roundCounter);
        anagram = (TextView) findViewById(R.id.anagram);
        answer = (EditText)findViewById(R.id.answer);
        enterGuess = (Button) findViewById(R.id.enterGuess);

        //Initialize round counter and anagram
        roundCounter.setText(currentRound + "/" + totalRounds);
        anagram.setText(generateAnagram(word));

        enterGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterGuess.getText().toString().equals("ENTER")) {                              //If button is in ENTER mode

                    if (answer.getText().toString().toLowerCase().equals(word.toLowerCase())) {     //Check if inputted guess is correct
                        anagram.setText("SUCCESS");
                        score++;
                    } else {
                        anagram.setText("INCORRECT\n"+word);
                    }
                    nextRound();                                                                    //Set button to NEXT ROUND mode

                } else if (enterGuess.getText().toString().equals("NEXT ROUND")) {                  //If button is in NEXT ROUND mode
                    resetGame();                                                                    //reset for next round

                } else if (enterGuess.getText().toString().equals("SEE RESULTS")) {                  //If button is in SEE RESULTS mode
                    startActivity(new Intent(getApplicationContext(), Results.class));              //Go to results page
                }
            }
        });
    }

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

    private void nextRound() {
        //Set text of button for going to next round
        if(currentRound == totalRounds) {
            enterGuess.setText("SEE RESULTS");
        } else {
            enterGuess.setText("NEXT ROUND");
            word = getWord();
        }
    }

    private void resetGame() {
        //Reset the anagram, increment round counter, set button for entering an answer
        currentRound++;
        roundCounter.setText(currentRound + "/" + totalRounds);
        word = getWord();
        anagram.setText(generateAnagram(word));
        answer.setText("");
        answer.setHint("Enter answer here...");
        enterGuess.setText("ENTER");
    }

    private String generateAnagram(String input) {
        //Shuffles the letters in a word to generate an anagram
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

    private void initializeWords(Activity activity)
            throws XmlPullParserException, IOException {
         words = new ArrayList<>();
        Resources res = activity.getResources();
        XmlResourceParser xml = res.getXml(R.xml.words);
        int eventType = xml.getEventType();
        while(eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.TEXT) {
                words.add(xml.getText());
            }
            eventType = xml.next();
        }
    }

    private String getWord() {
        return words.get((int) Math.round(Math.random() * (words.size() - 1)));
    }

    public static int getScore(){
        int finalScore = score;
        return finalScore;
    }
}
