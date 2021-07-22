package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class GameView extends SurfaceView {

    private SurfaceHolder holder ;
    private float x=600 ,y = 30 ;
    private float xSpeed =10 , ySpeed = 10 ;
    private Paint mPaint;
    private Rect rect ;
    private GameThread gameThread ;
     public GameView(Context context) {
         super(context);
         init(null);
         holder = getHolder() ;
         gameThread = new GameThread(this);
         holder.addCallback(new SurfaceHolder.Callback() {
             @Override
             public void surfaceCreated(@NonNull SurfaceHolder holder) {
                 gameThread.setRunning(true);
                 gameThread.start();
             }

             @Override
             public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

             }

             @Override
             public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
                 boolean retry = true ;
                 gameThread.setRunning(false);
                 while(retry){
                     try{
                         gameThread.join();
                         retry = false ;
                     } catch (InterruptedException e ){ }
                 }
             }
         });
     }


     public GameView(Context context, AttributeSet attrs) {
         super(context, attrs);
         init(attrs);
     }

     public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
         super(context, attrs, defStyleAttr);
         init(attrs);
     }

     @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
     public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
         super(context, attrs, defStyleAttr, defStyleRes);
         init(attrs);
     }

     private void init(@Nullable AttributeSet set ) {

     }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(x<0 || x > getWidth()){
            xSpeed*=-1;
        }
        if(y<0 || y> getHeight()){
            ySpeed*=-1;
        }
        x+=xSpeed;
        y+=ySpeed;
        canvas.drawColor(Color.BLACK);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG) ;
        mPaint.setColor(Color.RED);
        canvas.drawCircle(x,y,40f,mPaint);
    }
}
