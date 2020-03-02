package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    LinearLayout playActivityLayout;
    ImageView firstBotCube, secondBotCube, firstPlayerCube, secondPlayerCube;
    TextView botScore, playerScore;
    Random random = new Random(6);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playActivityLayout = findViewById(R.id.playActivityLayout);
        firstBotCube = findViewById(R.id.firstBotCube);
        secondBotCube = findViewById(R.id.secondBotCube);
        firstPlayerCube = findViewById(R.id.firstPlayerCube);
        secondPlayerCube = findViewById(R.id.secondPlayerCube);

        playActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstBotCube.setImageResource(R.drawable.six);

            }
        });
    }

}
