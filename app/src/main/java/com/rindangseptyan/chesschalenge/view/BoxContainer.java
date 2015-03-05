package com.rindangseptyan.chesschalenge.view;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rindangseptyan.chesschalenge.base.ApplicationBase;
import com.rindangseptyan.chesschalenge.chessboard.ModelBox;

/**
 * Created by rindangseptyan on 3/3/15.
 */
public class BoxContainer extends ImageView {
    public BoxContainer(Context context) {
        super(context);
    }

    public BoxContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoxContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setModel(ModelBox model){
        setPoint(model.p);
        setImageResource(model.icon);
//        setText(""+model.p.x+" | "+model.p.y);
    }

    private void setPoint(Point p){
        switch((p.x + p.y) % 2){
            case 0 : setBackgroundResource(android.R.color.black);break;
            case 1 : setBackgroundResource(android.R.color.white);break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = ApplicationBase.getBoxSize();
        layoutParams.width = ApplicationBase.getBoxSize();
        setLayoutParams(layoutParams);
        setMeasuredDimension(ApplicationBase.getBoxSize(),ApplicationBase.getBoxSize());
    }
}
