package com.example.aliosama.quraanapp.MainPackge;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aliosama.quraanapp.R;

import java.util.ArrayList;

public class SoraActivity extends Activity {
    ImageView mImageView;
    Button BackBtn;
    Button PlayBtn;
    Button PauseBtn;
    Button StopBtn;
    TextView SoraTitle;
    TextView SoraNumber;
    int SoraImage;
    int SoraAudio;
    String Title;
    String Number;
    private MediaPlayer mediaPlayer;
    private boolean stop = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sora);
       try {
           Intent mIntent = getIntent();
           Title = mIntent.getExtras().getString("Title");
           Number = mIntent.getExtras().getString("Number");
           SoraImage = mIntent.getExtras().getInt("Drawable");
           SoraAudio = mIntent.getExtras().getInt("Audio");
           mImageView = (ImageView) findViewById(R.id.SoraImageView);
           BackBtn = (Button) findViewById(R.id.BackBtn);
           PlayBtn = (Button) findViewById(R.id.PlayBtn);
           PauseBtn = (Button) findViewById(R.id.PauseBtn);
           StopBtn = (Button) findViewById(R.id.StopBtn);
           SoraTitle = (TextView) findViewById(R.id.SoraTitle);
           SoraNumber = (TextView) findViewById(R.id.SoraNumber);

           mImageView.setImageResource(SoraImage);
           mediaPlayer = MediaPlayer.create(SoraActivity.this,SoraAudio);
           SoraTitle.setText(Title);
           SoraNumber.setText(Number);

       }catch (Exception e){
           e.printStackTrace();
       }

    }
    public void Click(View v){
        int id = v.getId();
        switch (id){
            case R.id.BackBtn:
                finish();
                break;
            case R.id.PlayBtn:
                if(stop)
                    mediaPlayer = MediaPlayer.create(SoraActivity.this,SoraAudio);
                mediaPlayer.start();
                break;
            case R.id.PauseBtn:
                stop = false;
                mediaPlayer.pause();
                break;
            case R.id.StopBtn:
                stop = true;
                mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

}
