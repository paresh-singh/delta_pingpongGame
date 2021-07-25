package views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.pomggamedelta.R;

public class GameView extends SurfaceView {

    private float x=600 ,y = 30 ;
    private float xSpeed =10 , ySpeed = 10 ;
    private Paint mPaint,paintRect;
    private Rect rect ;
    private GameThread gameThread ;
    private float currX = 600f ;
    private Bitmap bmp ;

     public GameView(Context context) {
         super(context);
         init(null);
         SurfaceHolder holder = getHolder();
         gameThread = new GameThread(this);
         bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ball2);
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
         rect = new Rect() ;
         paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
         paintRect.setColor(Color.GREEN);
         mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
         mPaint.setColor(Color.RED);
     }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currX = event.getX();
        invalidate();
        return super.onTouchEvent(event);
    }

    private Bitmap resizeImage(Bitmap bitmap, int newSize){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int newWidth = 0;
        int newHeight = 0;

        if(width > height){
            newWidth = newSize;
            newHeight = (newSize * height)/width;
        } else if(width < height){
            newHeight = newSize;
            newWidth = (newSize * width)/height;
        } else if (width == height){
            newHeight = newSize;
            newWidth = newSize;
        }

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                width, height, matrix, true);

        return resizedBitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // rectangle
        rect.left = 650;
        rect.bottom = getHeight() - 230;
        rect.right = rect.left + 300;
        rect.top = rect.bottom + 40;
        Bitmap bmp2 = resizeImage(bmp , 100);

        rect.set((int)(currX-200), rect.top, (int)(currX+200), rect.bottom);
        //boundary
        if(x<0 || x  > getWidth() - 50){
            xSpeed*=-1;
        }
        if(y<0 ){
            ySpeed*=-1;
        }
        // if ( y ==0)
        if ( x > rect.left && x < rect.right){
            if(y>rect.top-80){
                ySpeed*=-1.02;
            }
        }
        x+=xSpeed;
        y+=ySpeed;

        // canvas draw

        canvas.drawColor(Color.BLACK);
       // canvas.drawBitmap( bmp2,  x , y ,null );
        canvas.drawRect(rect , paintRect);
        canvas.drawCircle(x, y, 40f, mPaint);

    }

}
