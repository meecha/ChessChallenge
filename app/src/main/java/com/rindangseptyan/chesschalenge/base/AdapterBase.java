package com.rindangseptyan.chesschalenge.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by rindangseptyan on 2/27/15.
 */
public abstract class AdapterBase<MODEL> extends BaseAdapter {
    protected LayoutInflater inflater = null;
    protected Activity activity;
    protected ArrayList<MODEL> list = new ArrayList<>();
    private ViewHolderBase<MODEL> viewHolder;

    public AdapterBase(Activity activity) {
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<MODEL> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ArrayList<MODEL> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MODEL getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            viewHolder = getViewHolder();
            vi = viewHolder.getView(inflater, parent, position);
            vi.setTag(viewHolder);
        } else
            viewHolder = (ViewHolderBase<MODEL>) vi.getTag();
        viewHolder.setAsset(getItem(position));
        return vi;
    }

    public abstract ViewHolderBase<MODEL> getViewHolder();
}