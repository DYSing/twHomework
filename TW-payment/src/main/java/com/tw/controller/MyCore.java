package com.tw.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import com.tw.bill.*;

import com.tw.bill.rule.BaseBillRule;
import org.springframework.beans.factory.annotation.Autowired;

public class MyCore {

    public MyCore() {
    }

    /**
     * 实际执行时的入口位置
     *
     * @param bill 未计算价格的商品列表
     */
    public GoodsBillWithPrice action(GoodsBill bill) {

        List<IBillRule> iBillRuleList = loadRule();

        GoodsBillWithPrice goodsBillWithPrice = null;
        boolean isLastRule = false;
        int rulesCount = iBillRuleList.size();
        for (IBillRule iBillRule : iBillRuleList) {
            if (--rulesCount == 0) isLastRule = true;
            System.out.println("当前执行规则名称：" + iBillRule.getRuleName());
            System.out.println("当前执行规则简介：" + iBillRule.getRuleInfo());
            if (goodsBillWithPrice == null) {
                //执行第一条规则时gbwp为null
                goodsBillWithPrice = iBillRule.getPriceByRule(bill.getGoodsList(), null, isLastRule);
//				gbwpl.add(goodsBillWithPrice);
            } else {
                //从第二条规则开始gbwp非null，将之前处理结果中没有优惠的商品在进行处理
                List<Goods> noDiscuontGoodsList = goodsBillWithPrice.getNoDiscountGoodsListWithSumPrice();
                List<Goods> emptyList = new ArrayList<Goods>();
                //因为再次对gbwp中noDiscuontGoodsList进行处理，先清空该列表，不然结果会重复
                goodsBillWithPrice.setGoodsListWithSumPrice(emptyList);
                iBillRule.getPriceByRule(noDiscuontGoodsList, goodsBillWithPrice, isLastRule);
//				gbwpl.add(priceByRule);
            }
        }
        printMessage(goodsBillWithPrice);
        return goodsBillWithPrice;
    }

    /**
     * 服务端打印最后的凭条
     *
     * @param goodsBillWithPrice 处理后的商品凭条信息
     */
    private void printMessage(GoodsBillWithPrice goodsBillWithPrice) {
        System.out.println(goodsBillWithPrice.getPrintMessage());
    }

    public void setiBillRuleList(List<IBillRule> iBillRuleList) {
        this.iBillRuleList = iBillRuleList;
    }

    public List<IBillRule> getiBillRuleList() {
        return iBillRuleList;
    }

    @Autowired
    private List<IBillRule> iBillRuleList;



    /**
     * 直接返注入的iBillRule对象列表
     *
     * @return 规则列表
     */
    public List<IBillRule> loadRule() {
        return iBillRuleList;
    }
    /**
     * 加载折扣规则
     *
     * @param ruleInfo 输入规则信息对象
     * @return rule
     */
    public IBillRule loadRule(RuleInfo ruleInfo) {
    String jarName = ruleInfo.getRuleFullPathWithJarFileName();
    File file = new File(jarName);
    URL url;
    IBillRule rule = null;
    try {
    url = file.toURL();
    System.out.println("加载路径： " + url);
    URLClassLoader loader = new URLClassLoader(new URL[]{url});
     @SuppressWarnings("rawtypes") Class ruleClass = loader.loadClass(ruleInfo.getClassName());
     rule = (IBillRule) ruleClass.newInstance();
     rule.setRuls(ruleInfo.getRULS());
     } catch (MalformedURLException e) {
     e.printStackTrace();
     } catch (ClassNotFoundException e) {
     e.printStackTrace();
     } catch (InstantiationException e) {
     e.printStackTrace();
     } catch (IllegalAccessException e) {
     e.printStackTrace();
     } finally {
     //如果获取规则失败则使用最基本的规则
     if (rule == null)
     rule = new BaseBillRule();
     }
     return rule;
     }
}
