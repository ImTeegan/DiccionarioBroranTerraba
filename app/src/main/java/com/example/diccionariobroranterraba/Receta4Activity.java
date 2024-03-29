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

public class Receta4Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta4);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta4);

        RecyclerView recyclerView = findViewById(R.id.receta4Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescado1mdpi, R.raw.surprise, "zhír"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescado2mdpi, R.raw.surprise, "bö́mcuo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescado3mdpi, R.raw.surprise, "quë́zhbon"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescado4mdpi, R.raw.surprise, "c’ö́s"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescado5mdpi, R.raw.surprise, "ibë́huo"));

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescadoenhoja1mdpi, R.raw.surprise, "bómcuo yë́i bo c’róga iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescadoenhoja2mdpi, R.raw.surprise, "c’róga póbguëi éjic"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescadoenhoja3mdpi, R.raw.surprise, "pobgó roi pírga yë́i jón̈ yocséa iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.pescadoenhoja4mdpi, R.raw.surprise, "e huorcuë́i"));


        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta4Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta4Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}