package com.example.randomnumberguesser;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class random_game extends AppCompatActivity {

    // Setting target guess and getting username from MainActivity
    private final Random random = new Random();
    private final int target = random.nextInt(100) + 1;
    public static final String result = "com.example.randomnumberguesser.result";


    // Incremental counter
    private int i = 0;

    // Creating layout references
    private EditText guess;
    private TextView GuessCounter;
    private TextView TryAgain;
    private TextView temp;
    private TextView hint;
    private Button btnGuess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_game);

        //Getting username from MainActivity
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.username);

        // Initialising layout variables and setting values
        guess = findViewById(R.id.edTxtGuess);
        GuessCounter = findViewById(R.id.txtGuessCounter);
        TryAgain = findViewById(R.id.txtTryAgain);
        btnGuess = findViewById(R.id.btnGuess);
        hint = findViewById(R.id.txtHint);

        temp = findViewById(R.id.textView5);
        temp.setText(String.valueOf(target));

        // TextWatcher to make sure input isn't empty or exceed max limit
        guess.addTextChangedListener(guessTextWatcher);

        // Setting init text
        TextView txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(String.format("Hello %s to the \n " +
                                        "Random Number Guessing Game! \n \n" +
                                        "It's as simple as guessing a number   \n" +
                                        "between 1 and 100 with 10 tries!", name.trim()));
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void onGuess(View view){

        String guessInptStr = guess.getText().toString().trim();


        // Making Intent object
        Intent gameOver = new Intent(this, game_over.class);

        // Winning Condition
        if (guessInptStr.equals(String.valueOf(target))){
            gameOver.putExtra(result, "1");
            startActivity(gameOver);
        }

        //Game lost
        else if (i == 10){
            gameOver.putExtra(result, "0");
            startActivity(gameOver);
        }

        else if (i >= 4){
            hint.setText( hint(Integer.parseInt(guessInptStr), target) );
        }
        i++;
        //Updating Counter and Resetting EditText
        GuessCounter.setText(String.format("Number of Guesses : %d", this.i));
        TryAgain.setText("Try again!");
        guess.getText().clear();

    }

    // Setting up the TextWatcher

    private final TextWatcher guessTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Extracting the input, trimming it, and converting it into int
            String guessInptStr = guess.getText().toString().trim();
            // (Integer.parseInt(guessInptStr) is just int version of guessInptStr
            btnGuess.setEnabled( (!guessInptStr.isEmpty()) && ((Integer.parseInt(guessInptStr) <= 100) && (Integer.parseInt(guessInptStr) >= 1) ));
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     *  Returns hints for users
     */
    private String hint(int input, int target){
        int difference = (target-input);
        int absdiff = abs(difference);
        String rval = "";

        if      (absdiff <=5)    { rval+="Really close! "; }
        else if (absdiff <= 10)  { rval+="Getting closer! "; }
        else if (absdiff <= 20)  { rval+="Still ways to go! "; }
        else { rval+="Really far away! "; }

        if (difference > 0){rval+="Go higher!";}
        else{rval+="Go Lower!";}

        return rval;
    }
}