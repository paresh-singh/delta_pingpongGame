package com.example.pomggamedelta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import views.CustomView;
import views.DrawTest;
import views.GameView;
import views.GameView2;

public class MainActivity extends AppCompatActivity {

    GameView myView ;
    RelativeLayout layout ;
    FrameLayout joiner ;

    private CustomView mCustomView ;
    private Button btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Button but1 = new Button(this ) ;
        Button but2 = new Button(this ) ;
        myView = new GameView(this) ;
        layout = new RelativeLayout(this ) ;
        joiner = new FrameLayout(this ) ;
        joiner.addView(myView);
        joiner.addView(layout);
        setContentView(joiner);

       // setContentView(new GameView(this));


    }

}