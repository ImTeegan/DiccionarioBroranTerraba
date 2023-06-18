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

public class Receta13Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta13);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta13);

        RecyclerView recyclerView = findViewById(R.id.receta13Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz1mdpi, R.raw.surprise, "ë́b e c’rë́i ga"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz2mdpi, R.raw.surprise, "ë́b cuóta dói órcuo go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz3mdpi, R.raw.surprise, "c’uocói"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz4mdpi, R.raw.surprise, "yë́i dŕó shco dabár shquín̈ \n" +
                "dánuruo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz5mdpi, R.raw.surprise, "yë́i jón̈ dí iroi shco dabár\n" +
                "shquín"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz6mdpi, R.raw.surprise, "ë́b dí iroi huénhuoŕo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz7mdpi, R.raw.surprise, "huéno pírga cŕói\n" +
                "apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz8mdpi, R.raw.surprise, "ë́b shó ŕorhuë́i dí\n" +
                "t’oc zbí iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz9mdpi, R.raw.surprise, "cuóta ŕashái píre"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz10mdpi, R.raw.surprise, "ŕíi jón̈ zbí iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz11mdpi, R.raw.surprise, "ŕorhuë́i yádo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz12mdpi, R.raw.surprise, "shó shíi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz13mdpi, R.raw.surprise, "sho shíi pírga ŕorhuë́i\n" +
                "dí t’oc igö́c obrë́ go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz14mdpi, R.raw.surprise, "yë́i jón̈ zë́n"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz15mdpi, R.raw.surprise, "srórbo dió yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chichademaiz16mdpi, R.raw.surprise, "yë́i jón̈ dabár cuón̈ shpáctën huoŕo"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta13Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta13Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}