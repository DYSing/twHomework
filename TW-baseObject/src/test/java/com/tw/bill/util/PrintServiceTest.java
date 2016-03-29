package com.tw.bill.util;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.constant.BillConstant;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY'sing on 2016-03-28.
 */
public class PrintServiceTest {

    PrintService printService = new PrintService();
    GoodsBillWithPrice goodsBillWithPrice = new GoodsBillWithPrice();
    List<Goods> noDiscountGoodsList = new ArrayList<Goods>();
    List<Goods> discountGoodsList = new ArrayList<Goods>();
    List<String> normalDiscountRuleList = new ArrayList<String>();
    Goods no_discount_goods1;
    Goods no_discount_goods2;
    Goods normal_discount_goods1;
    Goods normal_discount_goods2;

    @Before
    public void BeforeTest() {

        no_discount_goods1 = new Goods("ID1", "G1", 10.0d, 5.0d, BillConstant.GoodsType.G);
        no_discount_goods1.setgSumPrice(50d);
        no_discount_goods2 = new Goods("ID2", "G2", 20.0d, 1.0d, BillConstant.GoodsType.G);
        no_discount_goods2.setgSumPrice(20d);

        normal_discount_goods1 = new Goods("ID3", "G3", 30.0d, 1.0d, BillConstant.GoodsType.G);
        normal_discount_goods1.setgSumPrice(27.0d);
        normal_discount_goods1.setDiscountName("九五折");
        normal_discount_goods1.setgExtMessageWhenPrint("节省3(元)");

        normal_discount_goods2 = new Goods("ID4", "G4", 40.0d, 3.0d, BillConstant.GoodsType.G);
        normal_discount_goods2.setgSumPrice(80d);
        normal_discount_goods2.setDiscountName("买二赠一");
        normal_discount_goods2.setgDiscountNum(1.0d);


    }

    @Test
    public void get_print_message_when_just_have_no_discount_goods() {
        //两个不含优惠的商品
        noDiscountGoodsList.add(no_discount_goods1);
        noDiscountGoodsList.add(no_discount_goods2);
        goodsBillWithPrice.setGoodsListWithSumPrice(noDiscountGoodsList);
        goodsBillWithPrice.setTotalAmount(70);
        String printMessage = printService.getPrintMessage(goodsBillWithPrice);
        System.out.println(printMessage);
    }

    @Test
    public void get_print_message_when_just_have_normal_discount_goods() {
        //两个不含优惠的商品
        noDiscountGoodsList.add(no_discount_goods1);
        noDiscountGoodsList.add(no_discount_goods2);
        //一个普通优惠商品
        discountGoodsList.add(normal_discount_goods1);
        goodsBillWithPrice.setGoodsListWithSumPrice(noDiscountGoodsList);
        goodsBillWithPrice.setDiscountGoodsListWithSumPrice(discountGoodsList);
        goodsBillWithPrice.setTotalAmount(97.0d);
        //设置普通优惠商品优惠规则的名称
        normalDiscountRuleList.add("九五折");
        printService.setNormalDiscountRuleList(normalDiscountRuleList);

        String printMessage = printService.getPrintMessage(goodsBillWithPrice);
        System.out.println(printMessage);
    }

    @Test
    public void get_print_message_when_have_1_normal_and_1_special_discount_goods() {
        //两个不含优惠的商品
        noDiscountGoodsList.add(no_discount_goods1);
        noDiscountGoodsList.add(no_discount_goods2);
        //一个普通优惠商品
        discountGoodsList.add(normal_discount_goods1);
        //一个特殊优惠商品
        discountGoodsList.add(normal_discount_goods2);
        goodsBillWithPrice.setGoodsListWithSumPrice(noDiscountGoodsList);
        goodsBillWithPrice.setDiscountGoodsListWithSumPrice(discountGoodsList);
        goodsBillWithPrice.setTotalAmount(177.0d);
        //设置普通优惠商品优惠规则的名称
        normalDiscountRuleList.add("九五折");
        printService.setNormalDiscountRuleList(normalDiscountRuleList);

        String printMessage = printService.getPrintMessage(goodsBillWithPrice);
        System.out.println(printMessage);
    }
}
