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

public class Receta18Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta18);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta18);

        RecyclerView recyclerView = findViewById(R.id.receta18Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao1mdpi, R.raw.surprise, "có bó cuía shcó ga e tö́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao2mdpi, R.raw.surprise, "drói pírga"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao3mdpi, R.raw.surprise, "có cuohuó shíi pírga yë́i dŕó shco dabár c’uón̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao4mdpi, R.raw.surprise, "yë́i jón̈ zbí iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao5mdpi, R.raw.surprise, "ŕorhuë́i jón̈ yádo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao6mdpi, R.raw.surprise, "cuohuó danshcóga pas pas ŕé shco ga e dáno tan"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao7mdpi, R.raw.surprise, "sírcuëi c’uŕë́ iroi zén huoŕo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao8mdpi, R.raw.surprise, "cuóta dói órcuo go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao9mdpi, R.raw.surprise, "froyë́i c’uŕë́ go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao10mdpi, R.raw.surprise, "cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao11mdpi, R.raw.surprise, "dí c’rírquei"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao12mdpi, R.raw.surprise, "Có sho yë́i dí c’ríc iroi pírga e ŕorhuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao13mdpi, R.raw.surprise, "québin̈ cóhuo cuia e súc í\n" +
                "e píshco ga"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao14mdpi, R.raw.surprise, "cuóta dói"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cacao15mdpi, R.raw.surprise, "có dió yë́i ibin̈ cuia súc i t’oc"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta18Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta18Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}