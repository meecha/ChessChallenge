package com.rindangseptyan.chesschalenge.chessboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rindangseptyan.chesschalenge.R;
import com.rindangseptyan.chesschalenge.base.ViewHolderBase;
import com.rindangseptyan.chesschalenge.view.BoxContainer;

import java.util.ArrayList;

/**
* Created by rindangseptyan on 3/4/15.
*/
public class HolderBox extends ViewHolderBase<ArrayList<ModelBox>> {
    private BoxContainer[] boxes;

    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent, int position) {
        View vi = inflater.inflate(R.layout.item_chessline, parent, false);
        boxes= new BoxContainer[]{
                (BoxContainer) vi.findViewById(R.id.boxA),
                (BoxContainer) vi.findViewById(R.id.boxB),
                (BoxContainer) vi.findViewById(R.id.boxC),
                (BoxContainer) vi.findViewById(R.id.boxD),
                (BoxContainer) vi.findViewById(R.id.boxE),
                (BoxContainer) vi.findViewById(R.id.boxF),
                (BoxContainer) vi.findViewById(R.id.boxG),
                (BoxContainer) vi.findViewById(R.id.boxH)};
        return vi;
    }

    @Override
    public void setAsset(ArrayList<ModelBox> models) {
        for (ModelBox model:models) boxes[model.p.x].setModel(model);
    }
}
