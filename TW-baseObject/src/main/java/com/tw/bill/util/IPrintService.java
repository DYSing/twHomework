package com.tw.bill.util;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBillWithPrice;

import java.util.List;

/**
 * 凭条信息生成类
 * Created by DY'sing on 2016-03-29.
 */
public interface IPrintService {

    /**
     * 获取打印的凭条信息
     *
     * @param goodsBillWithPrice 处理后的商品对象
     * @return 凭条信息的字符串
     */
    String getPrintMessage(GoodsBillWithPrice goodsBillWithPrice);

    /**
     * 将包含优惠的商品列表进行拆分
     *
     * @param discountGoodsListWithSumPrice 所有包含优惠的商品
     * @param normalGoodsArrayList          普通优惠
     * @param specialGoodsArrayList         特殊优惠
     */
    void LetDiscountGoodsListToTwoList(List<Goods> discountGoodsListWithSumPrice,
                                       List<Goods> normalGoodsArrayList,
                                       List<Goods> specialGoodsArrayList);

    /**
     * 判断商品优惠是否为一般优惠
     *
     * @param discountName 优惠名称
     * @return 一般优惠时返回True
     */
    boolean isNormalDiscount(String discountName);
}
