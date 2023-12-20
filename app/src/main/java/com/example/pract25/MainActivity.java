package com.example.pract25;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int mAmogus, mSalo, mOtVinta, mPrivet, mRunAngel, mPovezlo;
    private int mStreamID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }

        mAssetManager = getAssets();

        // получим идентификаторы
        mAmogus = loadSound("amogus.mp3");
        mSalo = loadSound("salosalosaloukranskesalo.mp3");
        mOtVinta = loadSound("otvinta.mp3");
        mPrivet = loadSound("oprivet.mp3");
        mRunAngel = loadSound("etotparenbyilizteh.mp3");
        mPovezlo = loadSound("povezlo.mp3");

        ImageButton AmogusImageButton = findViewById(R.id.Amogus);
        AmogusImageButton.setOnClickListener(onClickListener);

        ImageButton SaloImageButton = findViewById(R.id.SaloSalo);
        SaloImageButton.setOnClickListener(onClickListener);

        ImageButton OtVintaImageButton = findViewById(R.id.OtVinta);
        OtVintaImageButton.setOnClickListener(onClickListener);

        ImageButton OprivetImageButton = findViewById(R.id.Hello);
        OprivetImageButton.setOnClickListener(onClickListener);

        ImageButton RunAngelImageButton = findViewById(R.id.RunAngel);
        RunAngelImageButton.setOnClickListener(onClickListener);

        ImageButton PovezloImageButton = findViewById(R.id.Povezlo);
        PovezloImageButton.setOnClickListener(onClickListener);


        
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            int id = v.getId();

            if (id == R.id.Amogus)
            {
               playSound(mAmogus);
            }
            else if (id == R.id.Hello)
            {
                playSound(mPrivet);
            }
            else if (id == R.id.OtVinta)
            {
                playSound(mOtVinta);
            }
            else if (id == R.id.Povezlo)
            {
                playSound(mPovezlo);
            }
            else if (id == R.id.RunAngel)
            {
                playSound(mRunAngel);
            }
            else if (id == R.id.SaloSalo)
            {
                playSound(mSalo);
            }
        }
    };


    @TargetApi(Build.VERSION_CODES.TIRAMISU)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
        }
        return mStreamID;
    }

    private int loadSound(String fileName) {
        AssetFileDescriptor afd;
        try {
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Не могу загрузить файл " + fileName,
                    Toast.LENGTH_SHORT).show();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Для устройств до Android 5
            createOldSoundPool();
        } else {
            // Для новых устройств
            createNewSoundPool();
        }

        mAssetManager = getAssets();

        // получим идентификаторы
        mAmogus = loadSound("amogus.mp3");
        mSalo = loadSound("salosalosaloukranskesalo.mp3");
        mOtVinta = loadSound("otvinta.mp3");
        mPrivet = loadSound("oprivet.mp3");
        mRunAngel = loadSound("etotparenbyilizteh.mp3");
        mPovezlo = loadSound("povezlo.mp3");

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSoundPool.release();
        mSoundPool = null;
    }
}
