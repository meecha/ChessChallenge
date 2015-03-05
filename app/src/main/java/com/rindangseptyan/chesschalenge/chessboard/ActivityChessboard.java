package com.rindangseptyan.chesschalenge.chessboard;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rindangseptyan.chesschalenge.R;
import com.rindangseptyan.chesschalenge.base.AdapterBase;
import com.rindangseptyan.chesschalenge.base.ViewHolderBase;
import com.rindangseptyan.chesschalenge.push.ModelPush;
import com.rindangseptyan.chesschalenge.util.ClientDone;
import com.rindangseptyan.chesschalenge.util.ConnectionTask;
import com.rindangseptyan.chesschalenge.util.Parser;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;


public class ActivityChessboard extends ActionBarActivity {

    private AdapterBase<ArrayList<ModelBox>> adapter;

    private TextView pattern;
    private ListView listView;
    ArrayList<ArrayList<ModelBox>> list;

    private String serverIpAddress = "128.199.87.11";
    private int serverport = 7387;
    private ConnectionTask client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chessboard);
        initSocket();
        initView();
        adapter.setList(resetRender());
    }

    private void initSocket() {
        client = new ConnectionTask();
        client.execute();
    }

    private void initView(){
        pattern = (TextView) findViewById(R.id.layouthelper);
        listView = (ListView) findViewById(R.id.list);
        adapter = getAdapter();
        listView.setAdapter(adapter);
    }

    public AdapterBase<ArrayList<ModelBox>> getAdapter(){
        return  new AdapterBase<ArrayList<ModelBox>>(this) {
            @Override
            public ViewHolderBase<ArrayList<ModelBox>> getViewHolder() {
                return new HolderBox();
            }
        };
    }

    public void onEvent(ModelPush event) {
        ArrayList<ModelBox>list = Parser.getInstance().parse(event.message);
        changeNewIcons(list);
    }

    private ArrayList<ArrayList<ModelBox>> resetRender(){
        ArrayList<ArrayList<ModelBox>> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<ModelBox> innerList = new ArrayList<>();
            for (int j = 0; j < 8; j++)
                innerList.add(new ModelBox(R.drawable.transparent, j, i));
            list.add(innerList);
        }
        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        initSocket();
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().post(new ClientDone());
    }

    private void changeNewIcons(ArrayList<ModelBox> models){
        list = resetRender();
        for(ModelBox model : models)
            presetModelBox(list.get(model.p.y).get(model.p.x), model);
        handler.sendEmptyMessage(0);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.setList(list);
        }
    };

    private int pointToIdx(Point p){
        return (p.y * 8) + p.x;
    }

    private void presetModelBox(ModelBox ori, ModelBox change){
        ori.icon = change.icon;
    }
}