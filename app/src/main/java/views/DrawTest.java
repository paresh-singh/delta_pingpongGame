package views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class DrawTest extends View {

    private static final String TAG = "Desenho";
    private ShapeDrawable rectangle;
    private Paint paint;
    private float currX, currY;
    private Rect blue, gray;


    public DrawTest(Context context) {
        super(context);

        currX = 1;
        currY = 1;

        gray = new Rect(50,30,200,150);
        blue = new Rect(200,200,400,350);

        paint = new Paint();
        rectangle = new ShapeDrawable(new RectShape());
    }

    @Override
    public boolean isFocused() {
        Log.d(TAG, "View's On focused is called !");
        return super.isFocused();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currX = event.getX();
        currY = event.getY();
        invalidate();
        Log.d(TAG, "View's On touch is called! X= "+currX + ", Y= "+currY);
        return super.onTouchEvent(event);
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        //Custom View
        rectangle.getPaint().setColor(Color.GRAY);
        rectangle.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        rectangle.getPaint().setStrokeWidth(3);
        gray.set((int)(50+currX), (int)(30+currY), (int)(200+currX), (int)(150+currY));
        rectangle.setBounds(gray);
        gray = rectangle.getBounds();
        rectangle.draw(canvas);

        rectangle.getPaint().setColor(Color.BLUE);
        rectangle.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        rectangle.getPaint().setStrokeWidth(3);
        blue.set((int)(200+currX), (int)(200+currY), (int)(400+currX), (int)(350+currY));
        rectangle.setBounds(blue);
        blue = rectangle.getBounds();
        rectangle.draw(canvas);

    }

}