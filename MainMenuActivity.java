package com.example.brickbreaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonLogin = findViewById(R.id.button_login);
        Button buttonStart = findViewById(R.id.button_start);
        Button buttonLeaderboards = findViewById(R.id.button_leaderboards);
        Button buttonExit = findViewById(R.id.button_exit);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonLeaderboards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your Leaderboard Activity here
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
