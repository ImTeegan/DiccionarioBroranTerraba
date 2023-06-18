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

public class Receta1Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta1);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta1);

        RecyclerView recyclerView = findViewById(R.id.receta1Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada1, R.raw.surprise, "shtö́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada2, R.raw.surprise, "shrí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada3, R.raw.surprise, "c’uŕí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada4, R.raw.surprise, "shcuŕë́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada5, R.raw.surprise, "shuŕín̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada6, R.raw.surprise, "só"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada7, R.raw.surprise, "juón̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada8, R.raw.surprise, "dúpcuo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada9, R.raw.surprise, "zhguŕó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada10, R.raw.surprise, "srúc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada11, R.raw.surprise, "fö́ shuŕín̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.animalcarneahumada12, R.raw.surprise, "nepcuógra"));

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada1mdpi, R.raw.surprise, "óhua e zréi dí c’ŕíc iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada2mdpi, R.raw.surprise, "zóc shrín̈ i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada3, R.raw.surprise, "zë́i bóc chíra"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada4mdpi, R.raw.surprise, "së́n̈na yë́i dúrgo qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada5mdpi, R.raw.surprise, "drún̈ yë́i ba qu’in̈gó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada6mdpi, R.raw.surprise, "drún̈ zhurúi sën̈ t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada7mdpi, R.raw.surprise, "yë́i yón̈gro qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada8mdpi, R.raw.surprise, "qu’íniei c’róga go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada9mdpi, R.raw.surprise, "huojói síra siráé"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.carneahumada10mdpi, R.raw.surprise, "yë́i cuírque jón̈ ei"));




        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta1Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta1Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}