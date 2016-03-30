package com.tw.bill.network.server;

import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.util.IPrintService;
import com.tw.bill.util.InputMessageHandle;
import com.tw.controller.MyCore;

import java.io.*;
import java.net.Socket;

/**
 * Created by DY'sing on 2016-03-29.
 */
public class BillTcpServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    MyCore myCore;
    IPrintService iPrintService;


    public BillTcpServerThread(Socket server, MyCore myCore, IPrintService iPrintService) {
        this.myCore = myCore;
        this.iPrintService = iPrintService;
        try {
            this.socket = server;
            // 构造该会话中的输入输出流
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream(), "UTF-8"));
            out = new PrintWriter(socket.getOutputStream(), true);
            start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {

            // Communicate with client until "bye " received.
            while (true) {
                // 通过输入流接收客户端信息
                String line = in.readLine();
                if (line == null || "".equals(line.trim())) { // 是否终止会话
                    break;
                }
                System.out.println("Received   message:" + line);
                String outMessage = processInPutMessage(line);
                System.out.println("返回的信息："+outMessage);
                // 通过输出流向客户端发送信息

                out.println(outMessage+ System.getProperty("line.separator")+"#####><#####");
                out.flush();
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理输入信息
     * @param receiveMessage
     * @return
     */
    private String processInPutMessage(String receiveMessage) {
        GoodsBill goodsBill = InputMessageHandle.getGoodsBill(receiveMessage);
        GoodsBillWithPrice goodsBillWithPrice = myCore.action(goodsBill);
        String printMessage = iPrintService.getPrintMessage(goodsBillWithPrice);
        return printMessage;
    }
}

