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

public class DiccionarioActivity extends AppCompatActivity implements RecyclerAdapter.itemClickListener {
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
        setContentView(R.layout.activity_diccionario);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_diccionario);



        RecyclerView recyclerView = findViewById(R.id.diccionarioRecycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.adona, R.raw.suspenso, "Dona"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.agatito, R.raw.taantaantan, "Gatito"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animales, R.raw.trompeta, "Animales"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.arbol, R.raw.violin, "Arbol"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.adona, R.raw.suspenso, "Dona"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.agatito, R.raw.taantaantan, "Gatito"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animales, R.raw.trompeta, "Animales"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.arbol, R.raw.violin, "Arbol"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(DiccionarioActivity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(DiccionarioActivity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }




/*
    List<ListElement> elements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diccionario);

        init();
    }

    public void init() { // puede estar en un archivo
        elements = new ArrayList<>();
        elements.add(new ListElement("#775447", "Aguacate", "se borraraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        elements.add(new ListElement("#607d8b", "maiz", "se borraraaaa"));
        elements.add(new ListElement("#03a9f4", "Yisus", "se borraraaaa"));
        elements.add(new ListElement("#f44336", "Yogurt", "se borraraaaa"));

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(elements, this);
        RecyclerView recyclerView = findViewById(R.id.diccionarioRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);
    }*/
}



