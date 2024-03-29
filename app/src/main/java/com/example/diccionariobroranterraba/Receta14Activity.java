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

public class Receta14Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta14);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta14);

        RecyclerView recyclerView = findViewById(R.id.receta14Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz1mdpi, R.raw.surprise, "congó c’órga zë́i c’ŕó shco"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz2mdpi, R.raw.surprise, "cuóshcuëi píre"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz3mdpi, R.raw.surprise, "yë́i chírahua cará quë́zbán̈ qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz4mdpi, R.raw.surprise, "ibó yáco e c’ŕái apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz5mdpi, R.raw.surprise, "c’ŕái un̈ ber prún̈sho dí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz6mdpi, R.raw.surprise, "nepcuógra qu’ióyo e yë́i zbí iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz7mdpi, R.raw.surprise, "qu’ióyo jón̈ cŕishcóga\n" +
                "shó yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz8mdpi, R.raw.surprise, "shó ŕoŕoë́i ún̈ shún̈guo t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz9mdpi, R.raw.surprise, "c’uofrurún cuoshcuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz10mdpi, R.raw.surprise, "drún̈ yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz11mdpi, R.raw.surprise, "bó e yaco e ŕoŕoë́i ún̈\n" +
                "qu’ióyo iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz12mdpi, R.raw.surprise, "irórhuëi ún̈con̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz13mdpi, R.raw.surprise, "nepcuógra shó e zë́i bér\n" +
                "prochíra"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz14mdpi, R.raw.surprise, "ŕó prú shó huórbo benó\n" +
                "e ŕorhuë́i qu’ióyo t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz15mdpi, R.raw.surprise, "drún̈ yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz16mdpi, R.raw.surprise, "c’uofrurún yë́i sirá c’róga\n" +
                "qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz17mdpi, R.raw.surprise, "së́n̈na yë́i bóc’uará ba\n" +
                "qu’in̈gó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz18mdpi, R.raw.surprise, "c’uofrurún yë́i sirá óbi ba qu’in̈gó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz19mdpi, R.raw.surprise, "pobguë́i cróga ŕigó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz20mdpi, R.raw.surprise, "huórbo shpúc ba iró shco ŕë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz21mdpi, R.raw.surprise, "pobríi qu’ipcuó go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tamaldearroz22mdpi, R.raw.surprise, "yë́i zbí iroi ga e ŕíi c’oc miá díc"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta14Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta14Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}