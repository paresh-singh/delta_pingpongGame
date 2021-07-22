package views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.pomggamedelta.R;

import java.security.PrivateKey;


public class GameView extends SurfaceView {
    private final Bitmap bmp ;
    private SurfaceHolder holder ;
    private GameThread gameThread ;
    private int x = 0 ;
    private int y = 0 ;
    private int xSpeed = 10 ;
    private int ySpeed = 10 ;
    private Paint mPaint ;
    private float prevY ;
    private Rect rect2 ;

    public GameView(Context context) {
        super(context);
        bmp = BitmapFactory.decodeResource(getResources() , R.drawable.ball);
        holder = getHolder();
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

                    } catch (InterruptedException e ){

                    }
                }
            }
        });
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // boundary condition
        if(x<0 || x> getWidth()){
            xSpeed *= -1 ;
        }
        if(y<0 || y> getHeight()){
            ySpeed *= -1 ;
        }
        x += xSpeed;
        y += ySpeed;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        if(canvas != null){
            canvas.drawCircle(x , y , 40f , mPaint);
        }
     //   canvas.drawBitmap(bmp, x, 10, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y2 = getY();
        switch(event.getAction()){
            case MotionEvent.ACTION_MOVE:
                float dy = y2 - prevY;
        }
        return super.onTouchEvent(event);
    }
}
