package com.tw.bill.rule;

import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.controlle.ExecutionRules;

public class Ruls_Buy3Free1 extends ExecutionRules {

    public String[] MYRULS = {};

    /**
     * 打印时显示的分类头
     *
     * @return
     */
    public static String printName() {
        return "买二赠一商品：";
    }

    public void letGoodsToTwoListByDiscount(List<Goods> goodsList,
                                            List<Goods> discountGoods, List<Goods> noDiscountGoods) {
        if (MYRULS.length == 0) {
            //没有规则时，全部进入noDiscountGoods
            noDiscountGoods.addAll(goodsList);
            return;
        }
        for (Goods goods : goodsList) {
            Boolean getIt = false;
            for (String r : MYRULS) {
                //当ID在规则范围内，且数量大于3时才满足买2送1
                if ((goods.getgId()).equalsIgnoreCase(r) && goods.getgNum() >= 3) {
                    discountGoods.add(goods);
                    getIt = true;
                    break;
                }
            }
            if (!getIt) {
                noDiscountGoods.add(goods);
            }
        }
    }

    public void calculationDiscountGoods(List<Goods> goodsList, GoodsBillWithPrice gbwp) {
        for (Goods goods : goodsList) {
            goods.setDiscountName(getThisRuleName());
            double a = goods.getgNum();
            int b = (int) a / 3;//满足买二赠一的部分(3个一组，b表示存在的组的个数)
            double c = a - b * 3;//剩余部分
            double total1 = b * goods.getgPrice() * 2;
            double total2 = c * goods.getgPrice();
            double total3 = a * goods.getgPrice();
            goods.setgSumPrice(total1 + total2);
            goods.setgDiscountNum(b);
            goods.setgDiscount(total3 - (total1 + total2));
            goods.setDiscountName(getRuleName());
            gbwp.addGoodsToDiscountGoodsListWithSumPrice(goods, total1 + total2, total3 - (total1 + total2));
        }
        gbwp.setDiscountMessage(printDiscountMessage());
    }

    public static String getThisRuleName() {
        return "买二赠一";
    }

    public String getRuleName() {
        return getThisRuleName();
    }

    public String getRuleInfo() {
        return "“买二赠一”是指，每当买进两个商品，就可以免费再买一个相同商品。";
    }

    public String printDiscountMessage() {
        StringBuffer sb = new StringBuffer();
        List<Goods> goodsList = getDiscountGoodsList();
        if (goodsList.size() > 0) {
            sb.append("\n" + printName() + "\n");
            for (Goods goods : goodsList) {
                sb.append("名称：");
                sb.append(goods.getgName());
                sb.append("，数量：");
                sb.append(goods.getgDiscountNum());
                sb.append(goods.getgType().getValue());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public void setRuls(String[] s) {
        this.MYRULS = s;
    }
}
