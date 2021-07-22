package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class CustomView extends View {

    private Rect rect1 ;
    private Rect rect2 ;
    private Paint paint , mPaintCircle ;
    private Float cx = 100f , cy = 100f;
    private Float rad = 300f ;
    int a = getWidth() ;
    int b = getHeight() ;


    public CustomView(Context context) {

        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);

    }

    private void init(@Nullable AttributeSet set ){
//        rect1 = new Rect() ;
        rect2 = new Rect() ;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintCircle.setColor(Color.BLACK);
        paint.setColor(Color.GREEN);
    }

    public void swapColor(){
        paint.setColor(paint.getColor() == Color.GREEN ? Color.RED : Color.GREEN);
        postInvalidate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        rect1.left = 450;
//        rect1.top = 30;
//        rect1.right = rect1.left + 300;
//        rect1.bottom = rect1.top + 30;

        rect2.left = 450;
        rect2.bottom = getHeight() - 50;
        rect2.right = rect2.left + 300;
        rect2.top = rect2.bottom + 30;
        if(cx == 0f || cy == 0f){
            cx = getWidth()/2f ;
            cy = getHeight()/2f ;
        }

        canvas.drawCircle( cx , cy , rad , mPaintCircle);
//        canvas.drawRect(rect1 , paint);
        canvas.drawRect(rect2 , paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value =  super.onTouchEvent(event);

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: return true ;

            case MotionEvent.ACTION_MOVE : {
                float x = event.getX();
                float y = event.getY();

                double dx = Math.pow(x-cx , 2);
                double dy = Math.pow(y-cy , 2);

                if(dx +dy < Math.pow(rad , 2)){
                    cx = x ;
                    cy = y ;
                    postInvalidate();
                    return true ;
                }
                return value ;
            }
        }
        return value ;
    }


}
