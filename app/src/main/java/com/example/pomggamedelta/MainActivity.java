package com.example.pomggamedelta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import views.CustomView;
import views.DrawTest;
import views.GameView;
import views.GameView2;

public class MainActivity extends AppCompatActivity {

    private CustomView mCustomView ;
    private Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
       // setContentView(R.layout.activity_main);
      setContentView(new GameView(this));
      //  setContentView(new DrawTest(this));

    }

}