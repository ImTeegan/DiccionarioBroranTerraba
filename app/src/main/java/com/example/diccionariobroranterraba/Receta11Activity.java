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

public class Receta11Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta11);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta11);

        RecyclerView recyclerView = findViewById(R.id.receta11Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz1mdpi, R.raw.surprise, "ë́b cuó e ŕíi pehuó c’ór\n" +
                "prún̈sho go (gën̈mó c’ór)"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz2mdpi, R.raw.surprise, "cuoshcuë́i dí go cuóta\n" +
                "dói huaŕó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz3mdpi, R.raw.surprise, "dáno pírga cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz4mdpi, R.raw.surprise, "zrë́i igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz5mdpi, R.raw.surprise, "dí tíëi ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz6mdpi, R.raw.surprise, "zhurúi ñótso"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz7mdpi, R.raw.surprise, "drún̈ yë́i ba iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz8mdpi, R.raw.surprise, "zhurúi ñótso"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz9mdpi, R.raw.surprise, "shó sŕui ber cuógrinso pírga shopshíi ber quë́zban"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz10mdpi, R.raw.surprise, "súi dë́ zbípcuo go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz11mdpi, R.raw.surprise, "e huorcuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.tortillasdemaiz12mdpi, R.raw.surprise, "suc pítan̈ ga yë́i bu dúrgo qu’ín̈go c’uŕë́ iroi"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta11Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta11Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}