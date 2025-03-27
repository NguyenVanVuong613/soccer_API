package com.example.sports_analytics.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sports_analytics.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome); // layout hiện tại của bạn

        Button btnStart = findViewById(R.id.btn_start);
        Button btnIntro = findViewById(R.id.btn_intro);

        // Khi nhấn nút "Bắt đầu"
        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Đóng IntroActivity
        });

        // Khi nhấn nút "Giới thiệu"
        btnIntro.setOnClickListener(v -> {
            Intent intent = new Intent(IntroActivity.this, IntroInfoActivity.class);
            startActivity(intent);
        });
    }
}
