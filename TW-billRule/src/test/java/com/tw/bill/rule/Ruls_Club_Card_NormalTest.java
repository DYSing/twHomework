package com.tw.bill.rule;

import com.tw.bill.*;
import com.tw.bill.constant.BillConstant;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by DY'sing on 2016-03-16.
 */
public class Ruls_Club_Card_NormalTest {

    private List<Goods> goodsList = new ArrayList<Goods>();
    private GoodsBillWithPrice goodsBillWithPrice = new GoodsBillWithPrice();

    @Before
    public void init() {

        Goods g1 = new Goods("ID1", "商品1", 3, 9, BillConstant.GoodsType.G);
        Goods g2 = new Goods("ID2", "商品2", 3, 1, BillConstant.GoodsType.G);
        goodsList.add(g1);
        goodsList.add(g2);

        goodsBillWithPrice.setGoodsListWithSumPrice(goodsList);

    }


    @org.junit.Test
    public void should_return_3000_when_provide_a_null_club_card() {

        //should
        ClubCard clubCard = null;
        //when
        Ruls_Club_Card_Normal ruls_club_card_normal = new Ruls_Club_Card_Normal();
        ruls_club_card_normal.setClubCard(clubCard);
        GoodsBillWithPrice priceByRule = ruls_club_card_normal.getPriceByRule(goodsList, goodsBillWithPrice, true);
        //then
        double totalAmount = priceByRule.getTotalAmount();
        // System.out.println(totalAmount);
        assertEquals(totalAmount, 30.00, 0.0001);
    }

    @org.junit.Test
    public void should_return_2100_when_provide_a_normal_club_card() {
        //默认普通会员7折扣
        //should
        ClubCard clubCard = new ClubCard(BillConstant.ClubCardType.NORMAL);
        clubCard.setDiscount(0.7);
        //when
        Ruls_Club_Card_Normal ruls_club_card_normal = new Ruls_Club_Card_Normal();
        ruls_club_card_normal.setClubCard(clubCard);
        GoodsBillWithPrice priceByRule = ruls_club_card_normal.getPriceByRule(goodsList, goodsBillWithPrice, true);
        //then
        double totalAmount = priceByRule.getTotalAmount();
        // System.out.println(totalAmount);
        assertEquals(totalAmount, 21.00, 0.0001);
    }
    @org.junit.Test
    public void should_return_1050_when_provide_a_golden_club_card() {
        //默认普通会员7折扣
        //金牌会员折后5折
        //should
        ClubCard clubCard = new ClubCard(BillConstant.ClubCardType.GOLDEN);
        clubCard.setDiscount(0.7);
        clubCard.setGoldenDiscount(0.5);
        //when
        Ruls_Club_Card_Normal ruls_club_card_normal = new Ruls_Club_Card_Normal();
        ruls_club_card_normal.setClubCard(clubCard);
        GoodsBillWithPrice priceByRule = ruls_club_card_normal.getPriceByRule(goodsList, goodsBillWithPrice, true);
        //then
        double totalAmount = priceByRule.getTotalAmount();
        // System.out.println(totalAmount);
        assertEquals(totalAmount, 10.50, 0.0001);
    }
}