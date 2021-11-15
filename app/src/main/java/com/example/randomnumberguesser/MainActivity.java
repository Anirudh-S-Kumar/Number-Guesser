package com.example.randomnumberguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String username = "com.example.randomnumberguesser.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBtnNext(View view){

        // Getting user's name
        EditText edTxtName = findViewById(R.id.edTxtName);
        String IpName = edTxtName.getText().toString();

        //Intent to open another activity
        Intent intent1 = new Intent(this, random_game.class);
        intent1.putExtra(username, IpName);
        startActivity(intent1);
    }
}