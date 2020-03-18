package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    LinearLayout playActivityLayout;
    ImageView firstBotCube, secondBotCube, firstPlayerCube, secondPlayerCube;
    TextView botScore, playerScore;
    Random random = new Random();
    int randomBotFirst, randomBotSecond, randomPlayerFirst, randomPlayerSecond;
    int botScoreInt = 0, playerScoreInt = 0;
    boolean queue = true, checkScore = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        playActivityLayout = findViewById(R.id.playActivityLayout);
        firstBotCube = findViewById(R.id.firstBotCube);
        secondBotCube = findViewById(R.id.secondBotCube);
        firstPlayerCube = findViewById(R.id.firstPlayerCube);
        secondPlayerCube = findViewById(R.id.secondPlayerCube);
        botScore = findViewById(R.id.botScore);
        playerScore = findViewById(R.id.playerScore);

        playActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (queue) {
                    randomBotFirst = random.nextInt(6) + 1;
                    randomBotSecond = random.nextInt(6) + 1;
                    switch (randomBotFirst) {
                        case 1:
                            firstBotCube.setImageResource(R.drawable.one);
                            break;
                        case 2:
                            firstBotCube.setImageResource(R.drawable.two);
                            break;
                        case 3:
                            firstBotCube.setImageResource(R.drawable.three);
                            break;
                        case 4:
                            firstBotCube.setImageResource(R.drawable.four);
                            break;
                        case 5:
                            firstBotCube.setImageResource(R.drawable.five);
                            break;
                        case 6:
                            firstBotCube.setImageResource(R.drawable.six);
                            break;
                    }
                    switch (randomBotSecond) {
                        case 1:
                            secondBotCube.setImageResource(R.drawable.one);
                            break;
                        case 2:
                            secondBotCube.setImageResource(R.drawable.two);
                            break;
                        case 3:
                            secondBotCube.setImageResource(R.drawable.three);
                            break;
                        case 4:
                            secondBotCube.setImageResource(R.drawable.four);
                            break;
                        case 5:
                            secondBotCube.setImageResource(R.drawable.five);
                            break;
                        case 6:
                            secondBotCube.setImageResource(R.drawable.six);
                            break;
                    }
                    if (randomBotFirst == randomBotSecond) {
                        Toast toast = Toast.makeText(PlayActivity.this, "Выпали одинаковые кубики. Переброс", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        botScoreInt += (randomBotFirst + randomBotSecond);
                        queue = !queue;
                    }
                } else {
                    randomPlayerFirst = random.nextInt(6) + 1;
                    randomPlayerSecond = random.nextInt(6) + 1;
                    switch (randomPlayerFirst) {
                        case 1:
                            firstPlayerCube.setImageResource(R.drawable.one);
                            break;
                        case 2:
                            firstPlayerCube.setImageResource(R.drawable.two);
                            break;
                        case 3:
                            firstPlayerCube.setImageResource(R.drawable.three);
                            break;
                        case 4:
                            firstPlayerCube.setImageResource(R.drawable.four);
                            break;
                        case 5:
                            firstPlayerCube.setImageResource(R.drawable.five);
                            break;
                        case 6:
                            firstPlayerCube.setImageResource(R.drawable.six);
                            break;
                    }
                    switch (randomPlayerSecond) {
                        case 1:
                            secondPlayerCube.setImageResource(R.drawable.one);
                            break;
                        case 2:
                            secondPlayerCube.setImageResource(R.drawable.two);
                            break;
                        case 3:
                            secondPlayerCube.setImageResource(R.drawable.three);
                            break;
                        case 4:
                            secondPlayerCube.setImageResource(R.drawable.four);
                            break;
                        case 5:
                            secondPlayerCube.setImageResource(R.drawable.five);
                            break;
                        case 6:
                            secondPlayerCube.setImageResource(R.drawable.six);
                            break;
                    }
                    if (randomPlayerFirst == randomPlayerSecond) {
                        Toast toast = Toast.makeText(PlayActivity.this, "Выпали одинаковые кубики. Переброс", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        playerScoreInt += (randomPlayerFirst + randomPlayerSecond);
                        queue = !queue;
                        checkScore = true;
                    }
                }

                botScore.setText(String.valueOf(botScoreInt));
                playerScore.setText(String.valueOf(playerScoreInt));

                if (checkScore) {
                    if (botScoreInt >= 100 && playerScoreInt >= 100) {
                        Intent intentDraw = new Intent(PlayActivity.this, DrawActivity.class);
                        startActivity(intentDraw);
                    } else if (botScoreInt >= 100) {
                        Intent intentLose = new Intent(PlayActivity.this, LoseActivity.class);
                        startActivity(intentLose);
                    } else if (playerScoreInt >= 100) {
                        Intent intentWin = new Intent(PlayActivity.this, WinActivity.class);
                        startActivity(intentWin);
                    } else {
                        checkScore = false;
                    }
                }
            }
        });
    }



}
