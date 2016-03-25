package com.tw.bill.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.List;

import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.GoodsBillWithPriceList;
import com.tw.bill.RuleInfo;
import com.tw.bill.util.InputMessageHandle;
import com.tw.billcontroller.MyCore;

/**
 * 程序设计上是内网使用，考虑到效率先使用UDP协议
 *
 * @author DY'sing
 */
public class BillUdpServer implements IBillServer {

    /**
     * 创建服务端socket对象，在ServerInit方法中根据端口号初始化
     */
    static DatagramSocket socket = null;

    /**
     * 根据传入端口号初始化一个服务端监听
     *
     * @param portNum
     */
    public void ServerInit(List<Integer> portNum) {
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
    }

    /**
     * 启动服务
     *
     * @param rulesList 执行的优惠规则
     */
    public void ServerStart(List<RuleInfo> rulesList) {
        //创建一个data数组用于存放接收的信息
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            while (true) {
                socket.receive(packet);
                //启动一个新线程处理消息
                //TODO 下一步考虑加入线程池
                new Thread(new ServiceImp(packet, rulesList)).start();
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

    private DatagramPacket packet = null;
    List<RuleInfo> rulesList = null;

    public ServiceImp(DatagramPacket packet, List<RuleInfo> rulesList) {
        this.packet = packet;
        this.rulesList = rulesList;
    }

    public void run() {
        //收到的信息
        String receiveMessage = new String(packet.getData(), 0, packet.getLength());
        //TODO 添加log4j以后在这里输出一下日志
        System.out.println("服务端接收到消息：" + receiveMessage);
        System.out.println(packet.getAddress().getHostAddress() + "：" + packet.getPort() + "：" + receiveMessage);
        //客户端发送特定字符串，用于确认服务端使用哪个端口
        if (!"TEST_SERVER_ALIVE".equalsIgnoreCase(receiveMessage)) {
            //解析输入串，生成商品列表对象
            GoodsBill gb = InputMessageHandle.getGoodsBill(receiveMessage);
            //处理、打印、返回输出内容
            MyCore mc = MyCore.getMyCore();
            GoodsBillWithPrice gbwp = mc.action(gb, rulesList);
//			GoodsBillWithPriceList action = mc.action(gb , rulesList);
//			packet.setData(action.getFinalOutString().getBytes());
            packet.setData(gbwp.getPrintMessage().getBytes());
            BillUdpServer.response(packet);
        } else {
            packet.setData("SERVER_OK".getBytes());
            BillUdpServer.response(packet);
        }

    }


}
