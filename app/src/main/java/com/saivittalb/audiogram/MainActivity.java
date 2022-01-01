package com.saivittalb.audiogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.saivittalb.audiogram.R;

public class MainActivity extends AppCompatActivity {

    private ImageView menu_show;
    private MenuBuilder menuBuilder;
    private Button calibrateButton;
    private Button profileButton;
    private  Button startButton;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String usertest = FirebaseAuth.getInstance().getCurrentUser().toString();
        Log.d("TAG-LOG",usertest);

        menu_show = findViewById(R.id.show_menu);
        calibrateButton = findViewById(R.id.calibrationButton);
        profileButton = findViewById(R.id.profileButton);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ExaminationActivity.class));
            }
        });

        calibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CalibrationActivity.class));
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });

        menuBuilder = new MenuBuilder(this);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menu, menuBuilder);//this will show our menu list we add

        //Set item click listener
        menu_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPopupHelper optionMenu = new MenuPopupHelper(MainActivity.this,
                        menuBuilder,view);
                optionMenu.setForceShowIcon(true);

                menuBuilder.setCallback(new MenuBuilder.Callback() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemSelected(@NonNull MenuBuilder menu, @NonNull MenuItem item) {
                        if (item.getItemId() == R.id.nav_logout) {
                            FirebaseAuth.getInstance().signOut();
                            Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,StartActivity.class));
                            finish();
                            return true;
                        }
                        if (item.getItemId() == R.id.nav_about_application){
                            startActivity(new Intent(MainActivity.this,AboutAppActivity.class));
                            return true;
                        }
                        if (item.getItemId() == R.id.nav_audiogram_graph){
                            startActivity(new Intent(MainActivity.this,AboutGraphActivity.class));
                            return true;
                        }
                        return false;
                    }
                    @Override
                    public void onMenuModeChange(@NonNull MenuBuilder menu) {
                        //empty
                    }
                });

                optionMenu.show();
            }
        });
    }
}