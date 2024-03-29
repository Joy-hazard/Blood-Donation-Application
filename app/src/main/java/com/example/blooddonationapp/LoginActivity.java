package com.example.blooddonationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Vibrator;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final HospitalDBHelper HospitalDB = new HospitalDBHelper(this);


        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        EditText userName = (EditText)findViewById(R.id.UsernameTxt);
        EditText password = (EditText)findViewById(R.id.PasswordTxt);


        Button login = (Button)findViewById(R.id.LoginBtn);
        login.setOnClickListener(v -> {

            Cursor cursor = HospitalDB.fetchAllHospitals();
            while (!cursor.isAfterLast())
            {

                if (userName.getText().toString().equals(cursor.getString(3)) && password.getText().toString().equals(cursor.getString(4)))
                {


                    Intent i = new Intent(LoginActivity.this, HospitalHomeActivity.class);
                    startActivity(i);
                    userName.getText().clear();
                    password.getText().clear();
                    Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_SHORT).show();
                    return;
                }
                cursor.moveToNext();
            }

                vibrator.vibrate(400);
                Toast.makeText(getApplicationContext(), "Username or Password is Wrong!", Toast.LENGTH_SHORT).show();
        });

        TextView newDonorAccount = (TextView)findViewById(R.id.NewDonorAccountBtn);
        newDonorAccount.setOnClickListener(v -> {//go to new donor activity

            Intent i = new Intent(LoginActivity.this, RegisterDonorInfoActivity.class);
            startActivity(i);
        });

        TextView newHospitalAccount = (TextView)findViewById(R.id.NewHospitalAccountBtn);
        newHospitalAccount.setOnClickListener(v -> {

            Intent i = new Intent(LoginActivity.this, RegisterHospitalActivity.class);
            startActivity(i);
        });
    }
}