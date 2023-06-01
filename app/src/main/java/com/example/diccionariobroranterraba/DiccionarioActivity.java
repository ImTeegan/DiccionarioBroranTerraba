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

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.faguacate, R.raw.suspenso, "dobórba"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fciruela, R.raw.taantaantan, "cuórsosí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fsemillanegra, R.raw.trompeta, "shró"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpapaya, R.raw.violin, "zhuó/shguó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fguanabana, R.raw.suspenso, "shgushgú"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmango, R.raw.taantaantan, "dogóm"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdragon, R.raw.trompeta, "shón̈/shón̈guo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpinamdpi, R.raw.violin, "pón̈huo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcasmdpi, R.raw.violin, "cásh"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdiguenmomdpi, R.raw.violin, "díguen̈mo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbananomaduromdpi, R.raw.violin, "bin̈síhua/québin̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fzhocmdpi, R.raw.violin, "zhóc/bodá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fchilemdpi, R.raw.violin, "ibó frëbrë́ "));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbananoverdemdpi, R.raw.violin, "ibín̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmazorcamdpi, R.raw.violin, "ë́b"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpejibayemdpi, R.raw.violin, "shúb"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.flimonmdpi, R.raw.violin, "guënmó shpágro"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fguenmomdpi, R.raw.violin, "guënmó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuoronmdpi, R.raw.violin, "shuoŕón̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.febtermamdpi, R.raw.violin, "ë́b t'ë́rma"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuicmdpi, R.raw.violin, "shuíc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcuginmdpi, R.raw.violin, "c’úgin̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fhuerbamdpi, R.raw.violin, "huë́rba"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcomdpi, R.raw.violin, "có"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbecmdpi, R.raw.violin, "bë́c"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuimdpi, R.raw.violin, "shúi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ficmdpi, R.raw.violin, "íc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fjocomdpi, R.raw.violin, "jóco / jócuo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ftumdpi, R.raw.violin, "t'ú"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcrorbomdpi, R.raw.violin, "crörbó / huarbó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fyacomdpi, R.raw.violin, "yáco"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmielmdpi, R.raw.violin, "órdio"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.farrozmdpi, R.raw.violin, "c’uofrurún"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fniobshtamdpi, R.raw.violin, "shtahuó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ftapadulcemdpi, R.raw.violin, "srórbosho"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fsrorbomdpi, R.raw.violin, "srórbo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdrunmdpi, R.raw.violin, "drún̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fniobshtamdpi, R.raw.violin, "niobshtá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fhuevomdpi, R.raw.violin, "ácuor"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fnoriomdpi, R.raw.violin, "nório"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdiosimdpi, R.raw.violin, "dió sí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcodiomdpi, R.raw.violin, "có dió"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fomdpi, R.raw.violin, "ó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdimdpi, R.raw.violin, "dí"));






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



