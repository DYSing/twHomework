package com.tw.bill.network.server;


import com.tw.bill.util.IPrintService;
import com.tw.controller.MyCore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by DY'sing on 2016-03-29.
 */
public class BillTcpServer extends ServerSocket implements IBillServer {

    public void setMyCore(MyCore myCore) {
        this.myCore = myCore;
    }

    MyCore myCore;

    public void setiPrintService(IPrintService iPrintService) {
        this.iPrintService = iPrintService;
    }

    IPrintService iPrintService;

    public BillTcpServer() throws IOException {
    }

    public void ServerStart(List<Integer> portNum) throws IOException {
        ServerSocket listen = null;
        for (Integer num : portNum) {
            try {
                listen = new ServerSocket(num);
                System.out.println("使用端口" + num + "启动成功");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
        try {
            while (true) {
                Socket server = listen.accept();
                new BillTcpServerThread(server, myCore, iPrintService);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
