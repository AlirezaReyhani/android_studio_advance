package com.example.sample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button play,pause,stop;
    int lenght=0;
    SeekBar seekBar;
    Handler handler;
    Timer timer;
    private void prepareMusic(){
        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.a);
    }

    class UpdateSeekBar implements Runnable{
        @Override
                public void run(){
            seekBar.setProgress(mediaPlayer.getCurrentPosition());
            handler.postDelayed(this,200 );
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.btn_play);
        pause=findViewById(R.id.btn_pause);
        stop=findViewById(R.id.btn_stop);
        prepareMusic();
        handler=new Handler();
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(lenght);
                mediaPlayer.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    lenght = mediaPlayer.getCurrentPosition();
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                lenght=0;
                prepareMusic();
            }
        });
        seekBar=findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        UpdateSeekBar updateSeekBar=new UpdateSeekBar();
        handler.post(updateSeekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediaPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
