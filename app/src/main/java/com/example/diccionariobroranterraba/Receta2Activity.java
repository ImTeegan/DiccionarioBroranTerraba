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

public class Receta2Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta2);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta2);

        RecyclerView recyclerView = findViewById(R.id.receta2Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo1mdpi, R.raw.surprise, "guënmó shpágro dió yë́i \n" +
                "igö́c iroi drún̈ t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo2mdpi, R.raw.surprise, "zhán̈cógro e huorcuë́i pírga"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo3mdpi, R.raw.surprise, "sréi igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo4mdpi, R.raw.surprise, "coshcuë́i guënmó shpágro\n" +
                "dió t’oc un̈ drún̈ t’o bacóe"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo5mdpi, R.raw.surprise, "yë́i jón̈ huóshte sirá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo6mdpi, R.raw.surprise, "cuóshcuëi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo7mdpi, R.raw.surprise, "së́n̈na e cŕói apcuóta go píre"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo8mdpi, R.raw.surprise, "së́n̈na e yë́i búc igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo9mdpi, R.raw.surprise, "yáco, drún̈, guënmó shpágro dió yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo10mdpi, R.raw.surprise, "irórhuëi úne"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo11mdpi, R.raw.surprise, "zhán̈cógro e fríi ba huórbo go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo12mdpi, R.raw.surprise, "zhán̈cógro e huórbo bac’uëi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo13mdpi, R.raw.surprise, "së́n̈na e sréi ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo14mdpi, R.raw.surprise, "fríi ba huorbó calë́ go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo15mdpi, R.raw.surprise, "yë́i yón̈gro qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo16mdpi, R.raw.surprise, "qu’íniei c’róga go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.chorizo17mdpi, R.raw.surprise, "dógro shco ga zë́i bóc’uará chíra e"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta2Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta2Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}