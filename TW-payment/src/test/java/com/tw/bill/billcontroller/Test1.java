package com.tw.bill.billcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.tw.bill.ClubCard;
import com.tw.bill.Goods;
import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;

import com.tw.bill.constant.BillConstant.ClubCardType;
import com.tw.bill.constant.BillConstant.GoodsType;

import com.tw.billcontroller.MyCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class Test1 {


    @Autowired
    MyCore mc;

    @Test
    public void test5() {
        System.out.println();
        System.out.println();
        System.out.println("=========开始测试折扣规则--买二赠一和95折--LoadJar==========");
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 3, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);
        mc.action(bill);
        System.out.println("=========结束测试折扣规则--买二赠一--LoadJar==========");

    }

    @Test
    public void should_getTotalPrice_19point90_when_Two_Goods_are_rule_buy2give1() {
        //should
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 3, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);
        //when


        //then
        GoodsBillWithPrice action = mc.action(bill);
//		double totalAmount = 0;
//		for (GoodsBillWithPrice  gbwp : action.getGoodsBillWithPriceList()) {
//			totalAmount += gbwp.getTotalAmount();
//		}
        //result
        //总计：19.90（元）
        //Assert.assertTrue(action.getPrintMessage().contains("总计：19.90（元）"));
//		Assert.assertEquals(totalAmount, 19.90);

        assertEquals(19.90, action.getTotalAmount(), 0.001);
    }

    @Test
    public void should_return_1730_when_provide_a_normal_club_card() {
        //TODO 先完成网络功能，下一步完善规则
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 3, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);
        ClubCard clubCard = new ClubCard(ClubCardType.NORMAL);
        //then
        GoodsBillWithPrice action = mc.action(bill);
        //assertEquals(19.90, action.getTotalAmount(), 0.001);
        System.out.println(action.getTotalAmount());
    }
}