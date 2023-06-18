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

public class Receta10Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta10);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta10);

        RecyclerView recyclerView = findViewById(R.id.receta10Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz1mdpi, R.raw.surprise, "ë́b cuó e ŕíi pehuó c’ór\n" +
                "prún̈sho go (gën̈mó c’ór)"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz2mdpi, R.raw.surprise, "cuoshcuë́i dí go cuóta\n" +
                "dói huaŕó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz3mdpi, R.raw.surprise, "dáno pírga cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz4mdpi, R.raw.surprise, "dúrgo c’órga zë́i cŕó shco"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz5mdpi, R.raw.surprise, "cuóshcuëi píre"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz6mdpi, R.raw.surprise, "yë́i chírahua cará quë́zbán̈ qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz7mdpi, R.raw.surprise, "ë́b shó yë́i sirá c’róga\n" +
                "qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz8mdpi, R.raw.surprise, "shtahuó e së́n̈na dion sŕo sŕó\n" +
                "shó yë́i ba qu’in̈gó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz9mdpi, R.raw.surprise, "shó yë́i sirá óbi ba qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz10mdpi, R.raw.surprise, "pobguë́i cróga ŕigó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz11mdpi, R.raw.surprise, "huórbo shpúi ba iró shco ŕë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz12mdpi, R.raw.surprise, "pobríi qu’ipcuó go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldemaiz13mdpi, R.raw.surprise, "yë́i zbí iroi ga e ŕíi c’oc cuará béië"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta10Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta10Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}