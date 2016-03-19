package com.tw.bill.rule;

import com.tw.bill.ClubCard;
import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.controlle.ExecutionRules;

import java.util.List;

/**
 * Created by DY'sing on 2016-03-16.
 */
public class Ruls_Club_Card_Golden extends ExecutionRules {

    private ClubCard clubCard = new ClubCard();
    public String[] MYRULS = {};

    public void setRuls(String[] s) {
        this.MYRULS = s;
    }

    public String getRuleName() {
        return "会员-金卡优惠";
    }

    public String getRuleInfo() {
        return "在已经享有的优惠基础上在打9折";
    }

    /**
     * 因为会员规则是针对所有商品的，所以重写getPriceByRule方法和letGoodsToTwoListByDiscount
     *
     * @param bill
     * @param goodsBillWithPrice
     * @param isLastRuls
     * @return
     */
    public GoodsBillWithPrice getPriceByRule(List<Goods> bill, GoodsBillWithPrice goodsBillWithPrice, boolean isLastRuls) {
        if (goodsBillWithPrice == null) {
            goodsBillWithPrice = new GoodsBillWithPrice();
        }
        //letGoodsToTwoListByDiscount(bill, discountGoodsList, noDiscountGoodsList);
        List<Goods> discountGoodsListWithSumPrice = goodsBillWithPrice.getDiscountGoodsListWithSumPrice();
        List<Goods> noDiscountGoodsListWithSumPrice = goodsBillWithPrice.getNoDiscountGoodsListWithSumPrice();
        calculationDiscountGoods(discountGoodsListWithSumPrice, goodsBillWithPrice);
        calculationNoDiscountGoods(noDiscountGoodsListWithSumPrice, goodsBillWithPrice, isLastRuls);

        return goodsBillWithPrice;
    }


    public void calculationNoDiscountGoods(List<Goods> noDiscountGoodsList, GoodsBillWithPrice goodsBillWithPrice, boolean isLastRuls) {

        double discountPoint = 1;
        if (clubCard != null && clubCard.getDiscount() > 0 && clubCard.getDiscount() <= 1) {
            discountPoint = clubCard.getDiscount();
        }
        for (Goods goods : noDiscountGoodsList) {
            double gPrice = goods.getgPrice();
            double gNum = goods.getgNum();
            double gSumPriceWithoutDiscount = gPrice * gNum;
            double gSumPriceWithDiscount = gSumPriceWithoutDiscount * discountPoint;
            goods.setgSumPrice(gSumPriceWithDiscount);
            goods.setgDiscountNum(gNum);
            goods.setgDiscount(gSumPriceWithoutDiscount - gSumPriceWithDiscount);
            goods.setDiscountName(getRuleName());
            goodsBillWithPrice.addGoodsToDiscountGoodsListWithSumPrice(goods, gSumPriceWithDiscount,
                    gSumPriceWithoutDiscount - gSumPriceWithDiscount);
        }


    }

    public void letGoodsToTwoListByDiscount(List<Goods> goodsList, List<Goods> discountGoods, List<Goods> noDiscountGoods) {

    }

    public void calculationDiscountGoods(List<Goods> discountGoodsList, GoodsBillWithPrice goodsBillWithPrice) {

        double discountPoint = 1;
        if (clubCard != null && clubCard.getDiscount() > 0 && clubCard.getDiscount() <= 1) {
            discountPoint = clubCard.getDiscount();
        }
        for (Goods goods : discountGoodsList) {

            double gNum = goods.getgNum();
            double gSumPriceWithoutClubCardGolden = goods.getgSumPrice();
            double gSumPriceWithClubCardGolden = gSumPriceWithoutClubCardGolden * discountPoint;

            /**
             * 使用会员优惠的情况下的优惠金额
             */
            double discountMoneyWithClubCardGolden = gSumPriceWithoutClubCardGolden - gSumPriceWithClubCardGolden;

            double gSumPriceWithAllDiscount = gSumPriceWithoutClubCardGolden + gSumPriceWithClubCardGolden;
            goods.setgSumPrice(gSumPriceWithClubCardGolden);
            goods.setgDiscountNum(gNum);

            goods.setgDiscount(gSumPriceWithoutClubCardGolden+discountMoneyWithClubCardGolden);
            goods.setDiscountName(goods.getDiscountName()+"-"+getRuleName());
            goodsBillWithPrice.addGoodsToDiscountGoodsListWithSumPrice(goods, gSumPriceWithClubCardGolden,
                    gSumPriceWithAllDiscount);
        }

    }

    public String printDiscountMessage() {
        return null;
    }

    public ClubCard getClubCard() {
        return clubCard;
    }

    public void setClubCard(ClubCard clubCard) {
        this.clubCard = clubCard;
    }
}
