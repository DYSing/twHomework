package com.tw.bill.util;


import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于构造打印凭条的方法
 * Created by DY'sing on 2016-03-28.
 */
public class PrintService implements  IPrintService {

    private static String NEXT_LINE = "\r\n";//换行符
    private static String BILL_TITLE = "***<没钱赚商店>购物清单***";//标题
    private static String SPLIT_LINE = "-------------------------";//行分割线
    private static String SPLIT_WORLD = "    ";//行内分隔符

    private List<String> getNormalDiscountRuleList() {
        return normalDiscountRuleList;
    }

    public  void setNormalDiscountRuleList(List<String> normalDiscountRuleList) {
        this.normalDiscountRuleList = normalDiscountRuleList;
    }

    private List<String> normalDiscountRuleList;

    public String getPrintMessage(GoodsBillWithPrice goodsBillWithPrice) {

        StringBuilder stringBuilder = new StringBuilder();
        //打印便签头部
        stringBuilder.append(BILL_TITLE).append(NEXT_LINE);
        stringBuilder.append(SPLIT_LINE).append(NEXT_LINE);
        //打印不含优惠部分
        List<Goods> noDiscountGoodsListWithSumPrice = goodsBillWithPrice.getNoDiscountGoodsListWithSumPrice();
        for (Goods good : noDiscountGoodsListWithSumPrice) {
            stringBuilder.append(good.getgName()).append(SPLIT_WORLD)
                    .append(good.getgNum()).append(SPLIT_WORLD)
                    .append(good.getgType().getValue()).append(SPLIT_WORLD)
                    .append(good.getgSumPrice()).append(NEXT_LINE);
        }
        stringBuilder.append(SPLIT_LINE).append(NEXT_LINE);
        //拆分普通优惠和特殊优惠商品
        List<Goods> discountGoodsListWithSumPrice = goodsBillWithPrice.getDiscountGoodsListWithSumPrice();
        List<Goods> normalGoodsArrayList = new ArrayList<Goods>();//普通优惠商品
        List<Goods> specialGoodsArrayList = new ArrayList<Goods>();//特殊优惠商品
        LetDiscountGoodsListToTwoList(discountGoodsListWithSumPrice, normalGoodsArrayList, specialGoodsArrayList);
        //打印普通优惠部分（普通会员、95折等）
        for(Goods good :normalGoodsArrayList){
            stringBuilder.append(good.getgName()).append(SPLIT_WORLD)
                    .append(good.getgNum()).append(SPLIT_WORLD)
                    .append(good.getgType().getValue()).append(SPLIT_WORLD)
                    .append(good.getgSumPrice()).append(SPLIT_WORLD)
                    .append(good.getgExtMessageWhenPrint()).append(NEXT_LINE);
            //TODO 将getgExtMessageWhenPrint改成“优惠金额+单位”的形式
        }
        stringBuilder.append(SPLIT_LINE).append(NEXT_LINE);
        //打印特殊优惠部分（买二赠一等）
        for(Goods good :specialGoodsArrayList){
            stringBuilder.append(good.getgName()).append(SPLIT_WORLD)
                    .append(good.getgNum()).append(SPLIT_WORLD)
                    .append(good.getgType().getValue()).append(SPLIT_WORLD)
                    .append(good.getgSumPrice()).append(SPLIT_WORLD)
                    .append("优惠数量：")
                    .append(good.getgDiscountNum()).append(NEXT_LINE);
        }
        stringBuilder.append(SPLIT_LINE).append(NEXT_LINE);
        //打印合计金额
        stringBuilder.append("合计：").append(SPLIT_WORLD).append(goodsBillWithPrice.getTotalAmount()).append(NEXT_LINE);
        //打印便签底部
        stringBuilder.append(SPLIT_LINE).append(NEXT_LINE);
        stringBuilder.append("谢谢惠顾，真的没挣钱啊");
        return stringBuilder.toString();
    }

    /**
     * 将包含优惠的商品列表进行拆分
     *
     * @param discountGoodsListWithSumPrice 所有包含优惠的商品
     * @param normalGoodsArrayList          普通优惠
     * @param specialGoodsArrayList         特殊优惠
     */
    public void LetDiscountGoodsListToTwoList(List<Goods> discountGoodsListWithSumPrice, List<Goods> normalGoodsArrayList, List<Goods> specialGoodsArrayList) {

        for (Goods good : discountGoodsListWithSumPrice) {
            if (isNormalDiscount(good.getDiscountName())) {
                normalGoodsArrayList.add(good);
            }else{
                specialGoodsArrayList.add(good);
            }
        }
    }

    /**
     * 判断商品优惠是否为一般优惠
     *
     * @param discountName 优惠名称
     * @return 一般优惠时返回True
     */
    public boolean isNormalDiscount(String discountName) {
        List<String> normalDiscountRuleList = this.getNormalDiscountRuleList();
        if(normalDiscountRuleList == null || normalDiscountRuleList.size() == 0){
            return false;
        }
        for (String ruleName : normalDiscountRuleList) {
            if (ruleName.equalsIgnoreCase(discountName)) {
                return true;
            }
        }
        return false;
    }

}
