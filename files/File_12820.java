package com.didi.virtualapk.demo.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import com.didi.virtualapk.demo.utils.MyUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TCPServerService extends Service {

    private boolean mIsServiceDestoryed = false;
    private String[] mDefinedMessages = new String[] {
            "ä½ å¥½å•Šï¼Œå“ˆå“ˆ",
            "è¯·é—®ä½ å?«ä»€ä¹ˆå??å­—å‘€ï¼Ÿ",
            "ä»Šå¤©åŒ—äº¬å¤©æ°”ä¸?é”™å•Šï¼Œshy",
            "ä½ çŸ¥é?“å?—ï¼Ÿæˆ‘å?¯æ˜¯å?¯ä»¥å’Œå¤šä¸ªäººå?Œæ—¶è?Šå¤©çš„å“¦",
            "ç»™ä½ è®²ä¸ªç¬‘è¯?å?§ï¼šæ?®è¯´çˆ±ç¬‘çš„äººè¿?æ°”ä¸?ä¼šå¤ªå·®ï¼Œä¸?çŸ¥é?“çœŸå?‡ã€‚"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    private class TcpServer implements Runnable {

        @SuppressWarnings("resource")
        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                System.err.println("establish tcp server failed, port:8688");
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed) {
                try {
                    // æŽ¥å?—å®¢æˆ·ç«¯è¯·æ±‚
                    final Socket client = serverSocket.accept();
                    System.out.println("accept");
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        };
                    }.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket client) throws IOException {
        // ç”¨äºŽæŽ¥æ”¶å®¢æˆ·ç«¯æ¶ˆæ?¯
        BufferedReader in = new BufferedReader(new InputStreamReader(
                client.getInputStream()));
        // ç”¨äºŽå?‘å®¢æˆ·ç«¯å?‘é€?æ¶ˆæ?¯
        PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream())), true);
        out.println("æ¬¢è¿Žæ?¥åˆ°è?Šå¤©å®¤ï¼?");
        while (!mIsServiceDestoryed) {
            String str = in.readLine();
            System.out.println("msg from client:" + str);
            if (str == null) {
                break;
            }
            int i = new Random().nextInt(mDefinedMessages.length);
            String msg = mDefinedMessages[i];
            out.println(msg);
            System.out.println("send :" + msg);
        }
        System.out.println("client quit.");
        // å…³é—­æµ?
        MyUtils.close(out);
        MyUtils.close(in);
        client.close();
    }

}
