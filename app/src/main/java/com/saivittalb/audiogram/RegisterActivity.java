package com.saivittalb.audiogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.saivittalb.audiogram.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;
    private EditText fullname;
    private EditText location;
    private EditText phoneNumber;
    private FirebaseAuth auth;
    private final static String USER = "users";
    private User user;

    FirebaseDatabase database;
    DatabaseReference myRef;

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.Register);
        fullname = findViewById(R.id.fullname);
        location = findViewById(R.id.location);
        phoneNumber = findViewById(R.id.phoneNumber);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(USER);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_fullname = fullname.getText().toString();
                String txt_location = location.getText().toString();
                String txt_phoneNumber = phoneNumber.getText().toString();
                String txt_lastexaminaton = "No research";

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(RegisterActivity.this, "Complete the login fields", Toast.LENGTH_SHORT).show();
                } else if (txt_password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
                } else if (txt_fullname.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Full name is required", Toast.LENGTH_SHORT).show();
                } else {
                    user = new User(txt_fullname,txt_phoneNumber,txt_location,txt_email,txt_lastexaminaton);
                    registerUser(txt_email,txt_password);
                }
            }
        });


    }

    private void registerUser(String email1, String password1) {
        auth.createUserWithEmailAndPassword(email1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this,"Registration was successful!", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(RegisterActivity.this,StartActivity.class));
                    FirebaseUser user = auth.getCurrentUser();
                    updateUI(user);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Registration failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //method
    public void updateUI(FirebaseUser currentUser) {
        String keyid = auth.getUid();
        myRef.child(keyid).setValue(user); //adding user info to database
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}