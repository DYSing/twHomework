package com.tw.bill.util;



import com.tw.bill.GoodsBillWithPrice;

/**
 * 用于构造打印凭条的方法
 * Created by DY'sing on 2016-03-28.
 */
public class PrintService {

    public static String BILL_TITLE ="***<没钱赚商店>购物清单***";
    public static String SPLIT_LINE ="-------------------------";

    public static String getPringMessage(GoodsBillWithPrice goodsBillWithPrice){

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(BILL_TITLE);
        stringBuffer.append(SPLIT_LINE);





        return null;
    }

}
