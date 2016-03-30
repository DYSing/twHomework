package com.tw.controller;

import com.tw.bill.Goods;
import com.tw.bill.network.server.IBillServer;
import com.tw.bill.util.BillUtil;
import com.tw.bill.util.InputMessageHandle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainController {
    public MainController(){
    }
    IBillServer iBillServer;

    public void setiBillServer(IBillServer iBillServer) {
        this.iBillServer = iBillServer;
    }



    public void run() {
        BillUtil b = new BillUtil();


        //加载商品目录
        HashMap<String, Goods> allGoodsMap = b.getGoodsMapBySAX("TW-payment\\configs\\AllGoods.xml");
        InputMessageHandle.setAllGoodsMap(allGoodsMap);


        List<Integer> portList = new ArrayList<Integer>();
            /*实际上生效的还是一个端口
             *用列表是如果端口被占用就尝试下一个
			 *客户端拥有相同的列表，初始化客户端的时候逐一尝试
			 */
        //TODO 下一步从配置文件获取监听端口列表
        portList.add(8081);
        portList.add(8082);
        try {
            iBillServer.ServerStart(portList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
