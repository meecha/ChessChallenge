package com.rindangseptyan.chesschalenge.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rindangseptyan on 2/27/15.
 */
public abstract class ViewHolderBase<MODEL> {

    public abstract View getView(LayoutInflater inflater, ViewGroup parent, int position);
    public abstract void setAsset(MODEL model);
}
