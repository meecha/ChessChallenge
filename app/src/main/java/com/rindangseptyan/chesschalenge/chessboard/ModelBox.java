package com.rindangseptyan.chesschalenge.chessboard;

import android.graphics.Point;

/**
 * Created by rindangseptyan on 3/4/15.
 */
public class ModelBox{
    public int icon;
    public Point p;

    public ModelBox(int icon, Point p){
        this.icon = icon;
        this.p = p;
    }

    public ModelBox(int icon, int x, int y){
        this.icon = icon;
        this.p = new Point(x, y);
    }
}
