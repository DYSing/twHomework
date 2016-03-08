package com.tw.billcontroller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tw.bill.Goods;
import com.tw.bill.GoodsBill;
import com.tw.bill.GoodsBillWithPrice;
import com.tw.bill.IBillRule;
import com.tw.bill.RuleInfo;
import com.tw.bill.rule.BaseBillRule;

public class MyCore {

	private static MyCore mc;
	private MyCore(){}
	
	public static MyCore getMyCore(){
		if(mc == null){
			mc = new MyCore();
		}
		return mc;
	}
	/**
	 * 实际执行时的入口位置
	 * @param bill
	 * @param rules
	 */
	public GoodsBillWithPrice action(GoodsBill bill,List<RuleInfo> rules){
		
		//先对规则排序
		Collections.sort(rules,new Comparator<RuleInfo>() {
	          public int compare(RuleInfo o1, RuleInfo o2) {
	              return o1.getLevel().compareTo(o2.getLevel());
	          }
	      });
		GoodsBillWithPrice gbwp =null;
		boolean isLastRuls = false;
		int rulesCount = rules.size();
		for (RuleInfo ruleInfo : rules) {
			if(--rulesCount ==0)isLastRuls=true;
			IBillRule br = loadRule(ruleInfo);
			System.out.println("当前执行规则名称："+br.getRuleName());
			System.out.println("当前执行规则简介："+br.getRuleInfo());
			if(gbwp == null){
				//执行第一条规则时gbwp为null
				gbwp = br.getPriceByRule(bill.getGoodsList(),gbwp,isLastRuls);
			}else{
				//从第二条规则开始gbwp非null，将之前处理结果中没有优惠的商品在进行处理
				List<Goods> noDiscuontGoodsList = gbwp.getGoodsListWithSumPrice();
				List<Goods> emptyList = new ArrayList<Goods>();
				//因为再次对gbwp中noDiscuontGoodsList进行处理，先清空该列表，不然结果会重复
				gbwp.setGoodsListWithSumPrice(emptyList);
				br.getPriceByRule(noDiscuontGoodsList,gbwp,isLastRuls);
			}
		}
		printMessage(gbwp);
		return gbwp;
	}
	/**
	 * 服务端打印最后的凭条
	 * @param gbwp
	 */
	private void printMessage(GoodsBillWithPrice gbwp) {
		System.out.println(gbwp.getPrintMessage());
	}

	/**
	 * 加载折扣规则
	 * @param ruleInfo
	 * @return
	 */
	public IBillRule loadRule(RuleInfo ruleInfo){
		String jarName = ruleInfo.getRuleFullPathWithJarFileName();
		File file = new File(jarName);
		URL url;
		IBillRule rule = null;
		try {
		url = file.toURL();
		System.out.println("加载路径： "+url);
		URLClassLoader loader = new URLClassLoader(new URL[]{url});
		@SuppressWarnings("rawtypes")
		Class ruleClass = loader.loadClass(ruleInfo.getClassName());
		rule = (IBillRule) ruleClass.newInstance();
		rule.setRuls(ruleInfo.getRULS());
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			//如果获取规则失败则使用最基本的规则
			if(rule == null)
				rule = new BaseBillRule();
		}
		return rule;
	}
}
