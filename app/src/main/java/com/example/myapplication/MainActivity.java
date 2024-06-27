package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.TaiKhoan;

import Lab10.ProfileActivity;
import Lab10.UserPreferences;

public class MainActivity extends AppCompatActivity {
    Button btnDanhBa, btnMap, btnMusic, btnBroadCast, btnProfile, btnDanhBa_v2, btnDanhBa_v3, btnLogOut;
    private UserPreferences userPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Initialize
        init();

        userPreferences = new UserPreferences(this);

        btnDanhBa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lab06.DanhBaActivity.class);
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lab06.SearchLocationActivity.class);
                startActivity(intent);
            }
        });

        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lab08.ListMusicActivity.class);
                startActivity(intent);
            }
        });

        btnBroadCast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lab09.BroadCastActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Lab10.ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPreferences.setIsLoggedIn(false);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDanhBa_v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lab10.DanhBaActivity.class);
                startActivity(intent);
            }
        });

        btnDanhBa_v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lab11.DanhBaActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void init() {
        btnDanhBa = findViewById(R.id.listDanhBa);
        btnMap = findViewById(R.id.map);
        btnMusic = findViewById(R.id.baiHat);
        btnBroadCast = findViewById(R.id.broadCastBtn);
        btnProfile = findViewById(R.id.profileBtn);
        btnDanhBa_v2 = findViewById(R.id.listDanhBaV2);
        btnDanhBa_v3 = findViewById(R.id.listDanhBaV3);
        btnLogOut = findViewById(R.id.logoutBtn);

    }
}
