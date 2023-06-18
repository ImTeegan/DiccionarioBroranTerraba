package com.example.diccionariobroranterraba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class Receta8Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    ArrayList<ImageAndSoundModel> imageAndSoundModelArrayList;

    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener
            = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };

    private final MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_receta8);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta8);

        RecyclerView recyclerView = findViewById(R.id.receta8Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano1mdpi, R.raw.surprise, "québin̈ quësón̈ cuóta\n" +
                "shcuë́i c’uomiá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano2mdpi, R.raw.surprise, "diói dŕó go dabár cuón"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano3mdpi, R.raw.surprise, "cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano4mdpi, R.raw.surprise, "prún̈sho yë́i igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano5mdpi, R.raw.surprise, "ŕohuë́i québin̈ cuía\n" +
                "c’uoshquín̈ t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano6mdpi, R.raw.surprise, "prún̈sho yë́i dúrgo qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano7mdpi, R.raw.surprise, "pobguë́i cróga ŕigó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano8mdpi, R.raw.surprise, "huórbo shpúi ba iró shco ŕë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano9mdpi, R.raw.surprise, "pobríi qu’ipcuó go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldebanano10mdpi, R.raw.surprise, "ŕíi zbí iroi cóc cuará e béigo"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta8Activity.this, 2));
        recyclerView.setAdapter(adapter);
    }

    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public void itemClick(int position) {
        releaseMediaPlayer();

        ImageAndSoundModel imageAndSoundModel = imageAndSoundModelArrayList.get(position);

        int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

        if (result == mAudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            mMediaPlayer = MediaPlayer.create(Receta8Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}