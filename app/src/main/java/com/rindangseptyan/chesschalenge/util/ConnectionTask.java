package com.rindangseptyan.chesschalenge.util;

import android.os.AsyncTask;
import android.util.Log;

import com.rindangseptyan.chesschalenge.push.ModelPush;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import de.greenrobot.event.EventBus;

/**
 * Created by rindangseptyan on 3/4/15.
 */
public class ConnectionTask extends AsyncTask<Void, Void, Void> {

    private boolean connected = true;
    private String serverIpAddress = "128.199.87.11";
    private int serverport = 7387;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        connected = true;
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(ClientDone event) {
        connected = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
            Log.d("ClientActivity", "C: Connecting...");
            Socket socket = new Socket(serverAddr, serverport);
            while (connected) {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null && connected) {
                        Log.d("ServerActivity", line);
                        EventBus.getDefault().post(new ModelPush(line));
                    }
                    Log.d("ClientActivity", "C: Sent.");
                } catch (Exception e) {
                    Log.e("ClientActivity", "S: Error" + e.getMessage(), e);
                }
            }
            socket.close();
            Log.d("ClientActivity", "C: Closed.");
        } catch (Exception e) {
            Log.e("ClientActivity", "C: Error" + e.getMessage() + "--", e);
            connected = false;
        }
        return null;
    }
}