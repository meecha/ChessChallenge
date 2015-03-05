package com.rindangseptyan.chesschalenge.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by rindangseptyan on 2/28/15.
 */
public class ApplicationBase extends Application {
    private static ApplicationBase instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ApplicationBase() {
        instance = this;
    }

    public static ApplicationBase getInstance() {
        return instance;
    }

    public static int getBoxSize(){
        WindowManager wm = (WindowManager) getInstance().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return (size.x > size.y ? size.y : size.x) / 8;
    }
}
