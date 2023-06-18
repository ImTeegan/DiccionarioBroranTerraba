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

public class Receta7Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta7);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta7);

        RecyclerView recyclerView = findViewById(R.id.receta7Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco1mdpi, R.raw.surprise, "ibin̈ quësón̈ e shói béië"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco2mdpi, R.raw.surprise, "cuóta shcuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco3mdpi, R.raw.surprise, "yë́i idán dŕó i dabár cuón"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco4mdpi, R.raw.surprise, "dáno pírga cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco5mdpi, R.raw.surprise, "prún̈sho yë́i igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco6mdpi, R.raw.surprise, "ibín̈ cuía ŕopsaŕái ibín̈ quësón̈ prún̈sho t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco7mdpi, R.raw.surprise, "dí tië́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco8mdpi, R.raw.surprise, "shóishrui ún̈con"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.atoldebananoseco9mdpi, R.raw.surprise, "ŕíi zbí go dabár béië"));


        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta7Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta7Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}