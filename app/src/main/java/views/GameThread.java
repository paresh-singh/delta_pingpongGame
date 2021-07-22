package views;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

public class GameThread extends Thread {
    private GameView viewhere;
    private boolean running = false ;

    public GameThread(GameView view){
        this.viewhere = view ;
    }

    public void setRunning(boolean run){
        running = run ;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run() {
        super.run();
        while (running){
            Canvas c = null ;

            try {
                c = viewhere.getHolder().lockCanvas();

                synchronized (viewhere.getHolder()){
                    viewhere.onDraw(c);

                }
            } finally {
                if(c!= null){
                    viewhere.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }

}
