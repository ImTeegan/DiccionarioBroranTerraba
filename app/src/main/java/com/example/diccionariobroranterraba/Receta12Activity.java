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

public class Receta12Activity extends AppCompatActivity implements RecyclerAdapter.itemClickListener{

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
        setContentView(R.layout.activity_receta12);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_receta12);

        RecyclerView recyclerView = findViewById(R.id.receta12Recycler);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Creates an array of images and soundEffects
        imageAndSoundModelArrayList = new ArrayList<>();

        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz1mdpi, R.raw.surprise, "ë́b yë́i dan dŕó i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz2mdpi, R.raw.surprise, "dí tië́i ba iroi chíchirá"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz3mdpi, R.raw.surprise, "cŕói apcuóta go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz4mdpi, R.raw.surprise, "prún̈sho yë́i igö́c iroi"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz5mdpi, R.raw.surprise, "québin̈ cuia e cuóta shcuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz6mdpi, R.raw.surprise, "québin̈ cuia e ŕorhuë́i ë́b prún̈sho t’oc"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz7mdpi, R.raw.surprise, "ëb prún̈sho yë́i c’róga\n" +
                "qu’ín̈go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz8mdpi, R.raw.surprise, "shgrë́i órcuo go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz9mdpi, R.raw.surprise, "pobguë́i c’róga ŕigó"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz10mdpi, R.raw.surprise, "huórbo shpúi ba iró\n" +
                "shco ŕë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz11mdpi, R.raw.surprise, "yë́i zbí iroi ga súi\n" +
                "yocséa go"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz12mdpi, R.raw.surprise, "e huorcuë́i"));
        imageAndSoundModelArrayList.add(new ImageAndSoundModel(R.drawable.quequebananomaiz13mdpi, R.raw.surprise, "c’róga dói"));

        RecyclerAdapter adapter = new RecyclerAdapter(imageAndSoundModelArrayList, getApplicationContext(), this);
        recyclerView.setLayoutManager(new GridLayoutManager(Receta12Activity.this, 2));
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
            mMediaPlayer = MediaPlayer.create(Receta12Activity.this, imageAndSoundModel.getmAudioResourceID());
        }
        mMediaPlayer.start();
        mMediaPlayer.setOnCompletionListener(mCompletionListener);
    }
}