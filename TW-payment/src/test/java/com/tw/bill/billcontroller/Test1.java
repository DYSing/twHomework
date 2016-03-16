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
import com.tw.bill.GoodsBillWithPriceList;
import com.tw.bill.IBillRule;
import com.tw.bill.RuleInfo;
import com.tw.bill.constant.BillConstant.ClubCardType;
import com.tw.bill.constant.BillConstant.GoodsType;
import com.tw.bill.rule.BaseBillRule;
import com.tw.billcontroller.MyCore;

public class Test1 {


    /**
     * 通过直接调用进行价格计算
     */
    public void test1() {
        System.out.println("=========开始测试折扣规则--基本规则--直接调用==========");
        BaseBillRule b = new BaseBillRule();
        System.out.println(b.getRuleName());
        System.out.println(b.getRuleInfo());
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g = new Goods("ID1", "商品1", 1, 9, GoodsType.G);
        goodsMap.put(g.getgId(), g);
        bill.setGoodsMap(goodsMap);
        GoodsBillWithPrice gbwp = b.getPriceByRule(bill);
        System.out.println(gbwp.getTotalAmount());
        System.out.println(gbwp.toString());
        System.out.println("*******打印凭条*****************");
        System.out.println(gbwp.getPrintMessage());
        System.out.println("=========结束测试折扣规则--基本规则-直接调用==========");
    }

    public void test2() {
        System.out.println("=========开始测试折扣规则--基本规则-LoadJar==========");
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 1, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);
        MyCore mc = MyCore.getMyCore();
        String[] ruls = {"ID1", "ID2", "ID5", "ID6"};
        RuleInfo ruleInfo = new RuleInfo("BaseRule.jar", "rules", "com.tw.bill.rule.BaseBillRule", 1, ruls);
        IBillRule br = mc.loadRule(ruleInfo);
        System.out.println(br.getRuleName());
        System.out.println(br.getRuleInfo());
        GoodsBillWithPrice gbwp = br.getPriceByRule(bill.getGoodsList(), null, true);
        System.out.println("总金额" + gbwp.getTotalAmount());
        System.out.println(gbwp.toString());
        System.out.println("=========结束测试折扣规则--基本规则-LoadJar==========");
    }

    /**
     * 通过直接调用进行价格计算
     * <p/>
     * public void test3(){
     * System.out.println("=========开始测试折扣规则--买二赠一--直接调用==========");
     * Ruls_Buy3Free1 r = new Ruls_Buy3Free1();
     * System.out.println(r.getRuleName());
     * System.out.println(r.getRuleInfo());
     * GoodsBill bill = new GoodsBill();
     * HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
     * Goods g = new Goods("ID1","商品1", 3, 1,Goods.GoodsType.G);
     * goodsMap.put(g.getgId(), g);
     * Goods g1 = new Goods("ID2","商品2", 3, 5,Goods.GoodsType.G);
     * goodsMap.put(g1.getgId(), g1);
     * Goods g2 = new Goods("ID3","商品3", 3, 5,Goods.GoodsType.G);
     * goodsMap.put(g2.getgId(), g2);
     * bill.setGoodsMap(goodsMap);
     * GoodsBillWithPrice gbwp = r.getPriceByRule(bill.getGoodsList(),null);
     * System.out.println(gbwp.getTotalAmount());
     * System.out.println(gbwp.toString());
     * System.out.println("------开始打印凭条（优惠部分）------");
     * System.out.println(gbwp.getDiscountMessage());
     * System.out.println("------结束打印凭条（优惠部分）------");
     * System.out.println("=========结束测试折扣规则--基本规则-直接调用==========");
     * }
     */
    public void test4() {
        System.out.println();
        System.out.println();
        System.out.println("=========开始测试折扣规则--买二赠一--LoadJar==========");
        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 3, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);
        MyCore mc = MyCore.getMyCore();
        String[] ruls = {"ID1", "ID2", "ID5", "ID6"};
        RuleInfo ruleInfo = new RuleInfo("Ruls_Buy3Free1.jar", "rules", "com.tw.bill.rule.Ruls_Buy3Free1", 1, ruls);
        List<RuleInfo> rules = new ArrayList<RuleInfo>();
        rules.add(ruleInfo);
        mc.action(bill, rules);
        System.out.println("=========结束测试折扣规则--买二赠一--LoadJar==========");
    }

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
        MyCore mc = MyCore.getMyCore();
        String[] ruls1 = {"ID1"};
        RuleInfo ruleInfo1 = new RuleInfo("Ruls_Buy3Free1And95off.jar", "rules", "com.tw.bill.rule.Ruls_Buy3Free1", 1, ruls1);
        String[] ruls2 = {"ID2"};
        RuleInfo ruleInfo2 = new RuleInfo("Ruls_Buy3Free1And95off.jar", "rules", "com.tw.bill.rule.Ruls_AllGoods95", 2, ruls2);
        List<RuleInfo> rules = new ArrayList<RuleInfo>();
        rules.add(ruleInfo1);
        rules.add(ruleInfo2);
        mc.action(bill, rules);
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
        String[] ruls_1 = {"ID1"};
        RuleInfo ruleInfo1 = new RuleInfo("TW-billRule-1.0-SNAPSHOT.jar", "rules", "com.tw.bill.rule.Ruls_Buy3Free1", 1, ruls_1);
        String[] ruls_2 = {"ID2"};
        RuleInfo ruleInfo2 = new RuleInfo("TW-billRule-1.0-SNAPSHOT.jar", "rules", "com.tw.bill.rule.Ruls_AllGoods95", 2, ruls_2);
        List<RuleInfo> rules = new ArrayList<RuleInfo>();
        rules.add(ruleInfo1);
        rules.add(ruleInfo2);

        //then
        MyCore mc = MyCore.getMyCore();
        GoodsBillWithPrice action = mc.action(bill, rules);
//		double totalAmount = 0;
//		for (GoodsBillWithPrice  gbwp : action.getGoodsBillWithPriceList()) {
//			totalAmount += gbwp.getTotalAmount();
//		}
        //result
        //总计：19.90（元）
        //Assert.assertTrue(action.getPrintMessage().contains("总计：19.90（元）"));
//		Assert.assertEquals(totalAmount, 19.90);

        Assert.assertEquals(19.90, action.getTotalAmount());
    }

    //@Test
    public void should_return_1730_when_provide_a_normal_club_card() {

        GoodsBill bill = new GoodsBill();
        HashMap<String, Goods> goodsMap = new HashMap<String, Goods>();
        Goods g1 = new Goods("ID1", "商品1", 3, 9, GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 2, 1, GoodsType.G);
        goodsMap.put(g1.getgId(), g1);
        goodsMap.put(g2.getgId(), g2);
        bill.setGoodsMap(goodsMap);

        ClubCard clubCard = new ClubCard(ClubCardType.NORMAL);


        String[] ruls1 = {"ID1"};
        RuleInfo ruleInfo1 = new RuleInfo("Ruls_Buy3Free1And95off.jar", "rules", "com.tw.bill.rule.Ruls_Buy3Free1", 1, ruls1);
        String[] ruls2 = {"ID2"};
        RuleInfo ruleInfo2 = new RuleInfo("Ruls_Buy3Free1And95off.jar", "rules", "com.tw.bill.rule.Ruls_AllGoods95", 2, ruls2);
        List<RuleInfo> rules = new ArrayList<RuleInfo>();
        rules.add(ruleInfo1);
        rules.add(ruleInfo2);

        //then
        MyCore mc = MyCore.getMyCore();

        ruleInfo1.setClubCard(clubCard);
        ruleInfo2.setClubCard(clubCard);

//		GoodsBillWithPriceList action = mc.action(bill, rules);
//		System.out.println(action.getFinalOutString());
//		Assert.assertTrue(action.getFinalOutString().contains("总计：19.90（元）"));
        GoodsBillWithPrice action = mc.action(bill, rules);

    }
}