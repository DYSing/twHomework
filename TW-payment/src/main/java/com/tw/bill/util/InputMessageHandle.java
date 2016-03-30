package com.tw.bill.util;

import java.util.HashMap;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBill;

/**
 * 处理从客户端接收到的字符串，返回一个处理过的GoodsBill对象
 *
 * @author DY'sing
 */
public class InputMessageHandle {
    private static HashMap<String, Goods> allGoodsMap = new HashMap<String, Goods>();

    public static HashMap<String, Goods> getAllGoodsMap() {
        return allGoodsMap;
    }

    public static void setAllGoodsMap(HashMap<String, Goods> allGoodsMap) {
        InputMessageHandle.allGoodsMap = allGoodsMap;
    }

    /**
     * 解析输入串，生成商品列表对象
     *
     * @param inputsString
     * @return
     */
    public static GoodsBill getGoodsBill(String inputsString) {
        //1.校验输入的合法性
        if (allGoodsMap == null && inputsString == null && "".equals(inputsString) && !check(inputsString)) {
            return null;
        }
        //2.获取GoodsBill对象
        GoodsBill gb = doInputMessage(allGoodsMap, inputsString);
        return gb;
    }

    private static boolean check(String inputsString) {
        // TODO 校验输入参数是否合法，先全返回成功，下一阶段补充这个方法
        return true;
    }

    private static GoodsBill doInputMessage(HashMap<String, Goods> allGoodsMap,
                                            String inputsString) {
        //因为示例中的输入串虽然说是json，不过没有key，还是按照字符串解析吧
        String[] inputGoods = inputsString.replace("[", "").replace("]", "").split(",");
        HashMap<String, Double> inGoodsMap = new HashMap<String, Double>();
        HashMap<String, Goods> outGoodsMap = new HashMap<String, Goods>();
        for (String string : inputGoods) {
            String tempString = string.replaceAll("\'", "");
            String goodsId = "";
            double goodsNum = 0.0;
            if (tempString.indexOf("-") != -1) {
                String[] temp = tempString.split("-");
                goodsId = temp[0];
                goodsNum = Double.parseDouble(temp[1]);
            } else {
                goodsId = tempString;
                goodsNum = 1;
            }
            if (inGoodsMap.get(goodsId) != null) {
                inGoodsMap.put(goodsId, (inGoodsMap.get(goodsId)) + goodsNum);
            } else {
                inGoodsMap.put(goodsId, goodsNum);
            }
        }
        //System.out.println(goodsMap.size());
        for (String key : inGoodsMap.keySet()) {
            double goodsNum = inGoodsMap.get(key);
            Goods g = allGoodsMap.get(key);
            g.setgNum(goodsNum);
            outGoodsMap.put(key, g);
        }
        GoodsBill gb = new GoodsBill();
        gb.setGoodsMap(outGoodsMap);
        return gb;
    }
}
