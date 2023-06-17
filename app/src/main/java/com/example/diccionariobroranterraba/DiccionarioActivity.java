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

        //Faltan varios audios, los que faltan tienen asiganado el audio con el nombre surprise
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.faguacate, R.raw.surprise, "dobórba"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fciruela, R.raw.surprise, "cuórsosí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fsemillanegra, R.raw.surprise, "shró"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpapaya, R.raw.surprise, "zhuó/shguó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fguanabana, R.raw.surprise, "shgushgú"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmango, R.raw.surprise, "dogóm"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdragon, R.raw.surprise, "shón̈/shón̈guo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpinamdpi, R.raw.surprise, "pón̈huo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcasmdpi, R.raw.surprise, "cásh"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdiguenmomdpi, R.raw.surprise, "díguen̈mo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbananomaduromdpi, R.raw.bananomaduro, "bin̈síhua/québin̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fzhocmdpi, R.raw.pacaya, "zhóc/bodá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fchilemdpi, R.raw.chile, "ibó frëbrë́ "));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbananoverdemdpi, R.raw.platano, "ibín̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmazorcamdpi, R.raw.mazorca, "ë́b"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fpejibayemdpi, R.raw.surprise, "shúb"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.flimonmdpi, R.raw.surprise, "guënmó shpágro"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fguenmomdpi, R.raw.surprise, "guënmó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuoronmdpi, R.raw.surprise, "shuoŕón̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.febtermamdpi, R.raw.surprise, "ë́b t'ë́rma"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuicmdpi, R.raw.surprise, "shuíc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcuginmdpi, R.raw.surprise, "c’úgin̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fhuerbamdpi, R.raw.surprise, "huë́rba"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcomdpi, R.raw.cacao, "có"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fbecmdpi, R.raw.surprise, "bë́c"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fshuimdpi, R.raw.surprise, "shúi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ficmdpi, R.raw.yuca, "íc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fjocomdpi, R.raw.surprise, "jóco / jócuo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ftumdpi, R.raw.surprise, "t'ú"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcrorbomdpi, R.raw.surprise, "crörbó / huarbó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fyacomdpi, R.raw.culantrocoyote, "yáco"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fmielmdpi, R.raw.surprise, "órdio"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.farrozmdpi, R.raw.arroz, "c’uofrurún"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fniobshtamdpi, R.raw.surprise, "shtahuó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ftapadulcemdpi, R.raw.surprise, "srórbosho"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fsrorbomdpi, R.raw.canadulce, "srórbo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdrunmdpi, R.raw.sal, "drún̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fniobshtamdpi, R.raw.surprise, "niobshtá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fhuevomdpi, R.raw.huevo, "ácuor"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fnoriomdpi, R.raw.surprise, "nório"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdiosimdpi, R.raw.surprise, "dió sí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fcodiomdpi, R.raw.surprise, "có dió"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fomdpi, R.raw.surprise, "ó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.fdimdpi, R.raw.agua, "dí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uigocmdpi, R.raw.guacal, "igö́c"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uzgomdpi, R.raw.surprise, "zgö́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uzhuenmdpi, R.raw.cuchara, "zhuë́n̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.udronmdpi, R.raw.cuchillo, "drö́n̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uzbimdpi, R.raw.olla, "zbí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ucuremdpi, R.raw.batea, "c’uŕë́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.utushquimdpi, R.raw.molenillo, "t’ushquí"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uarashaclomdpi, R.raw.colador, "aŕashaclo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ucracomdpi, R.raw.piedramoler, "cŕáco/apcuóta"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.udurgomdpi, R.raw.hojabijagua, "dúrgo"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.ucongomdpi, R.raw.bijao, "con̈gó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uquipcuomdpi, R.raw.amarrabejuco, "qu’ipcuó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.uyongromdpi, R.raw.surprise, ""));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cshpamdpi, R.raw.surprise, "shpá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cquemdpi, R.raw.surprise, "quë́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cshpactenmdpi, R.raw.surprise, "shpácten̈ / shpoctén̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.cfrebremdpi, R.raw.surprise, "frëbrë́"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.baricdigomdpi, R.raw.surprise, "ba ŕíc dí go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.badionmdpi, R.raw.surprise, "ba dión̈"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.bashricmdpi, R.raw.surprise, "ba shríc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.basucmdpi, R.raw.surprise, "ba súc"));






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
}



