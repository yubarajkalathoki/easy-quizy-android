package com.easyquizy;

import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class popup extends AppCompatActivity {

    Boolean sound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.5), (int) (height*.3));
        disableSound();
    }

    public void disableSound(){
        final ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        final AudioManager amanager;
        amanager = (AudioManager)getSystemService(AUDIO_SERVICE);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sound = true;
                    // The toggle is enabled
                    toggle.setChecked(sound);
                    amanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

                    // turn on sound, enable notifications
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);

                    //notifications
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, false);

                    //alarm
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, false);

                    //ringer
                    amanager.setStreamMute(AudioManager.STREAM_RING, false);

                    //media
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
                } else {
                    sound = false;
                    toggle.setChecked(false);
                    // The toggle is disabled
                    //turn ringer silent
                    amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                    //turn off sound, disable notifications
                    amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
                    //notifications
                    amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
                    //alarm
                    amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
                    //ringer
                    amanager.setStreamMute(AudioManager.STREAM_RING, true);
                    //media
                    amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);

                }
            }
        });
    }
}
