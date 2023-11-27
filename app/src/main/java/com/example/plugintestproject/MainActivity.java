package com.example.plugintestproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private TextView randomIntegerTextView;
    private boolean isPlaying = false;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButton);
        randomIntegerTextView = findViewById(R.id.randomIntegerTextView);

        mediaPlayer = MediaPlayer.create(this, R.raw.song);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                togglePlayback();

                boolean isEven = count%2==0;
                if(isEven){
                    showRandomInteger();
                }
                else {
                    randomIntegerTextView.setText("Random number generation occurs when the button is \ntapped an even number of times.");
                }
            }
        });
    }

    private void showRandomInteger() {
        int random = generateRandomNumber(1,100);
        for (int i = 1; i < random; i++) {
            for (int j = 1; j < 3; j++) {
                int executionTimes = i*j;
                randomIntegerTextView.setText(String.valueOf("Executions times: " + executionTimes));
            }
        }
    }

    private void togglePlayback() {
        try {
            if (isPlaying) {
                stopSong();
            } else {
                playSong();
            }
        } catch (Exception e) {
            // Handle exceptions during playback toggling
        } finally {
            // Code that will be executed whether an exception is caught or not
        }
    }

    private void playSong() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
            isPlaying = true;
            playButton.setText("Pause");
        }
    }

    private void stopSong() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0); // Rewind to the beginning
            isPlaying = false;
            playButton.setText("Play");
        }
    }
    private static int generateRandomNumber(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min");
        }

        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}