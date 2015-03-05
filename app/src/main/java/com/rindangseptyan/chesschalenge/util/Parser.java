package com.rindangseptyan.chesschalenge.util;

import android.graphics.Point;

import com.rindangseptyan.chesschalenge.R;
import com.rindangseptyan.chesschalenge.chessboard.ModelBox;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rindangseptyan on 3/4/15.
 */
public class Parser {
    private static Parser instance;
    private HashMap<Character, Integer> icons;

    private Parser() {
        icons = new HashMap<>();
        icons.put('K', R.drawable.w_king);
        icons.put('Q', R.drawable.w_queen);
        icons.put('B', R.drawable.w_bishop);
        icons.put('N', R.drawable.w_knight);
        icons.put('R', R.drawable.w_rook);
        icons.put('k', R.drawable.b_king);
        icons.put('q', R.drawable.b_queen);
        icons.put('b', R.drawable.b_bishop);
        icons.put('n', R.drawable.b_knight);
        icons.put('r', R.drawable.b_rook);
    }

    public static Parser getInstance() {
        if (instance == null)
            instance = new Parser();
        return instance;
    }

    public int getIcon(Character code) {
        return icons.get(code) == null ? R.drawable.transparent : icons.get(code);
    }

    private int getColumn(Character code) {
        return (int) code - (int) 'a';
    }

    private int getRow(Character code) {
        return (int) code - (int) '1';
    }

    private Point getPoint(String code) {
        char col = code.charAt(1);
        char row = code.charAt(2);
        if (inBoxRange(getRow(row)) && inBoxRange(getColumn(col)))
            return new Point(getColumn(col), getRow(row));
        return null;
    }

    private boolean inBoxRange(int range) {
        return range >= 0 && range < 8;
    }

    public ArrayList<ModelBox> parse(String codes) {
        ArrayList<ModelBox> boxes = new ArrayList<>();
        for (String code : split(codes)) {
            if (parseModel(code) != null)
                boxes.add(parseModel(code));
        }
        return boxes;
    }

    private String[] split(String codes) {
        return codes.split("[ ]");
    }

    private ModelBox parseModel(String code) {
        if (code.length() != 3) return null;
        return new ModelBox(getIcon(code.charAt(0)), getPoint(code));
    }
}
