package com.example.randomnumberguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class game_over extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        String result = intent.getStringExtra(random_game.result).toString();
        TextView msg = findViewById(R.id.txtResult);
        if (result.equals(String.valueOf(0))){
            msg.setText("Unfortunate! \n You Lost");
        }
        else if (result.equals(String.valueOf(1))){
            msg.setText("Congratulations! \n You Won!");
        }
    }

    public void onPlay(View view){
        Intent game = new Intent(this, random_game.class);
        startActivity(game);
    }

    public void onFresh(View view){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }

}
