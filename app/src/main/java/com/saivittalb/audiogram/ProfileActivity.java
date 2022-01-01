package com.saivittalb.audiogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.saivittalb.audiogram.R;

public class ProfileActivity extends AppCompatActivity {

    private TextView emailTxv;
    private TextView fullname;
    private TextView location;
    private TextView lastexamination;
    private TextView phoneNumber;
    private ImageView imageUser;
    private User user;
    private final static String USER = "users";
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageUser = findViewById(R.id.userPhoto);
        emailTxv = findViewById(R.id.emailtext);
        fullname = findViewById(R.id.name_surname);
        location = findViewById(R.id.locationtext);
        phoneNumber = findViewById(R.id.phoneNumbertext);
        lastexamination = findViewById(R.id.lastexaminaton);

        auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();

        if(firebaseUser == null){
            Toast.makeText(this,"Something went wrong. User's details are not available",Toast.LENGTH_SHORT).show();
        }else{
            showUserProfile(firebaseUser);
        }

    }

    private void showUserProfile(FirebaseUser firebaseUser) {

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(USER);
        String keyid = auth.getUid();
        //Log.d("USER-DATA",keyid);

        myRef = FirebaseDatabase.getInstance().getReference().child(USER).child(keyid);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fullname_txt = snapshot.child("fullname").getValue().toString();
                String email_txt = snapshot.child("email").getValue().toString();
                String location_txt = snapshot.child("location").getValue().toString();
                String phoneNumber_txt = snapshot.child("phoneNumber").getValue().toString();
                String last_examination_txt = snapshot.child("lastExamination").getValue().toString();
                emailTxv.setText(email_txt);
                fullname.setText(fullname_txt);
                location.setText(location_txt);
                phoneNumber.setText(phoneNumber_txt);
                lastexamination.setText(last_examination_txt);
                // Log.d("USER-DATA",fullname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}