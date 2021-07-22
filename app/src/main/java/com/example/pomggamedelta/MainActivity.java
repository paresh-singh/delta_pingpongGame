package com.example.pomggamedelta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import views.CustomView;
import views.GameView;

public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView ;
    private Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        mCustomView = findViewById(R.id.customView) ;
//        btn = findViewById(R.id.button) ;
//
//        btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                mCustomView.swapColor();
//
//            }
//        });

        setContentView(new GameView(this));

    }

}