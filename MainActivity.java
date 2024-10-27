package com.example.brickbreaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.game_view);

        Button pauseButton = findViewById(R.id.button_pause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.pauseGame();
            }
        });

        Button resumeButton = findViewById(R.id.button_resume);
        resumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.resumeGame();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
