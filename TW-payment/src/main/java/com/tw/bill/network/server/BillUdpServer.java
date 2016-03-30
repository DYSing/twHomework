package com.tw.bill.network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;

import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.util.IPrintService;
import com.tw.bill.util.InputMessageHandle;
import com.tw.controller.MyCore;


/**
 * 程序设计上是内网使用，考虑到效率先使用UDP协议
 *
 * @author DY'sing
 */
public class BillUdpServer implements IBillServer {

    public void setMyCore(MyCore myCore) {
        this.myCore = myCore;
    }

    MyCore myCore;

    public void setiPrintService(IPrintService iPrintService) {
        this.iPrintService = iPrintService;
    }

    IPrintService iPrintService;
    /**
     * 创建服务端socket对象，在ServerInit方法中根据端口号初始化
     */
    static DatagramSocket socket = null;



    /**
     * 启动服务
     */
    public void ServerStart(List<Integer> portNum) {
        for (Integer num : portNum) {
            try {
                socket = new DatagramSocket(num);
                System.out.println("***服务端使用端口【" + num + "】启动成功！***");
                break;
            } catch (SocketException e) {
                e.printStackTrace();
                continue;//当初始化失败的时候，继续这个循环
            }
        }
        if (socket == null) {
            throw new RuntimeException("服务端初始化失败，端口被占用" + portNum.toString());
        }

        //创建一个data数组用于存放接收的信息
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            while (true) {
                socket.receive(packet);
                //启动一个新线程处理消息
                //TODO 下一步考虑加入线程池
                new Thread(new ServiceImp(packet, myCore, iPrintService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 给客户端返回处理结果
     *
     * @param packet
     */
    public static void response(DatagramPacket packet) {
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServiceImp implements Runnable {


    private MyCore myCore;
    private IPrintService iPrintService;

    private DatagramPacket packet = null;

    public ServiceImp(DatagramPacket packet, MyCore myCore, IPrintService iPrintService) {
        this.packet = packet;
        this.myCore = myCore;
        this.iPrintService = iPrintService;
    }

    public void run() {
        //收到的信息
        String receiveMessage = new String(packet.getData(), 0, packet.getLength());
        //TODO 添加log4j以后在这里输出一下日志
        System.out.println("服务端接收到消息：" + receiveMessage);

        //客户端发送特定字符串，用于确认服务端使用哪个端口
        if (!"TEST_SERVER_ALIVE".equalsIgnoreCase(receiveMessage)) {

            GoodsBill gb = InputMessageHandle.getGoodsBill(receiveMessage);
            //处理、打印、返回输出内容
            GoodsBillWithPrice goodsBillWithPrice = myCore.action(gb);
            byte[] sendBuf = goodsBillWithPrice.getPrintMessage().getBytes();
            DatagramSocket responseSocket =null;
            try {
                int port = packet.getPort();
                InetAddress address = packet.getAddress();
                DatagramPacket sendPacket
                        = new DatagramPacket(sendBuf, sendBuf.length , address , port );
                responseSocket = new DatagramSocket();
                responseSocket.send(sendPacket);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(responseSocket != null) responseSocket.close();
            }
//            BillUdpServer.response(packet);
        } else {
            packet.setData("SERVER_OK".getBytes());
            BillUdpServer.response(packet);
        }

    }


}
