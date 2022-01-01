package com.saivittalb.audiogram;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.saivittalb.audiogram.R;

public class CalibrationActivity extends AppCompatActivity {

    AudioManager audioManager;
    float leftVolume = 0.04f;
    float rightVolume = 0.04f;
    private static final String TAG = "CalibrationActivity";
    private MusicIntentReceiver myReceiver;
    MediaPlayer mp;
    Button buttonCalibration;
    Button buttonStart;
    Button buttonStop;
    AlertDialog.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibration);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        myReceiver = new MusicIntentReceiver();
        buttonStart = findViewById(R.id.startButton);
        buttonStop = findViewById(R.id.stopButton);
        buttonCalibration = findViewById(R.id.calibrationButton1);
        buttonStop.setEnabled(false);
        buttonCalibration.setEnabled(false);

        builder = new AlertDialog.Builder(this);


        buttonCalibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Only one tone should be heard when the calibration procedure has been correctly performed.\n\n" +
                        "If so - from now on, do not change the volume level of the device\n" +
                        "If not, repeat the calibration procedure")
                        .setCancelable(false)
                        .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(), "Now you can do a hearing test",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(), "Repeat the calibration procedure",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Calibration procedure");
                alert.show();
                mp.stop();
            }
        });
        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCalibration.setEnabled(true);
                buttonStop.setEnabled(true);
                mp = MediaPlayer.create(CalibrationActivity.this,R.raw.testzz);
                mp.setVolume(leftVolume, rightVolume);
                mp.start();
            }
        });
        TextView textView;
        ProgressBar progressBar;
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        textView = findViewById(R.id.percentText);
        progressBar = findViewById(R.id.progressBar);
        SeekBar seekBar = findViewById(R.id.seekBar);
        progressBar.setProgress(currentVolume);
        seekBar.setProgress(currentVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                progressBar.setProgress(progress);
                textView.setText("" + progress + "%");

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
    @Override
    public void onResume() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);
        super.onResume();
    }

    public static boolean isBluetoothHeadsetConnected() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()
                && mBluetoothAdapter.getProfileConnectionState(BluetoothHeadset.HEADSET) == BluetoothHeadset.STATE_CONNECTED;
    }

    public void calibrationHearSound(View view) {
    }

    private class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean device = isBluetoothHeadsetConnected();
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        if(!device) {
                            Log.d(TAG, "Headset is unplugged");
                            Toast.makeText(CalibrationActivity.this, "Please plug in the headphones", Toast.LENGTH_SHORT).show();
                            buttonCalibration.setEnabled(false);
                            buttonStart.setEnabled(false);
                            buttonStop.setEnabled(false);
                        }else{
                            Log.d(TAG, "Bluetooth Headset is plugged");
                            Toast.makeText(CalibrationActivity.this, "Plugged", Toast.LENGTH_SHORT).show();
                            buttonCalibration.setEnabled(false);
                            buttonStart.setEnabled(true);
                            buttonStop.setEnabled(true);
                            break;
                        }
                        break;
                    case 1:
                        Log.d(TAG, "Headset is plugged");
                        Toast.makeText(CalibrationActivity.this, "Plugged", Toast.LENGTH_SHORT).show();
                        buttonCalibration.setEnabled(true);
                        buttonStart.setEnabled(true);
                        buttonStop.setEnabled(true);
                        break;
                    default:
                        Log.d(TAG, "I have no idea what the headset state is");
                }
            }
        }
    }
    @Override
    public void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
        finish();
    }

}