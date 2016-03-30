package com.tw.bill.network.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by DY'sing on 2016-03-29.
 */
public class BillTcpClient {
    public static void main(String[] args) {
        BillTcpClient b = new BillTcpClient();
        b.callServer();
    }

    public void callServer() {
        try {
            Socket socket = new Socket(InetAddress.getByName("localhost"), 8081);

//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
//            bufferedWriter.write("客户端发送的测试消息" + System.getProperty("line.separator"));

//            OutputStream ops=socket.getOutputStream();
//            DataOutputStream dos = new DataOutputStream(ops);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(getInputString());
            printWriter.flush();
            //dos.writeUTF("客户端发送的测试消息" + System.getProperty("line.separator"));
//            dos.writeUTF(getInputString());
//            dos.write(getInputString().getBytes());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            do{
                stringBuilder.append( bufferedReader.readLine()).append(System.getProperty("line.separator"));
                //System.out.println("服务器返回："+stringBuilder.toString());
                System.out.println("服务器返回："+(i++));
            }while (!"#####><#####".equalsIgnoreCase(bufferedReader.readLine()));

            System.out.println("服务器返回zong：" + stringBuilder.toString());

//            bufferedWriter.close();
//            dos.close();
//            ops.close();
            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getInputString() {
        return "['ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001','ITEM000001'," +
                "'ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
    }
}
