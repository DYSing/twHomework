package com.tw.homework.test;


import com.tw.bill.IBillRule;
import com.tw.bill.rule.BaseBillRule;
//import com.tw.bill.rule.Ruls_Buy3Free1;
import com.tw.bill.rule.Ruls_Buy3Free1;
import com.tw.controller.MyCore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;





/**
 * Created by DY'sing on 2016-03-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class SpringTest {

    @Autowired
    private Ruls_Buy3Free1 ruls_Buy3Free1;

    @Autowired
    private BaseBillRule baseBillRule;

    @Autowired
    private MyCore  myCore;


    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        Object buy3for1free = context.getBean("ruls_Buy3Free1");
        IBillRule billRule = (IBillRule) buy3for1free;
        System.out.print(billRule.getRuleName());
    }


    @Test
    public void SpringMainTest(){
        assertEquals("基本价格规则",baseBillRule.getRuleName());
    }

    @Test
    public void Rule_Buy3Free1Test(){
        assertEquals("买二赠一",ruls_Buy3Free1.getRuleName());
    }

    @Test
    public void MyCoreTest(){
        List<IBillRule> ruleList = myCore.getiBillRuleList();
        System.out.println(ruleList.size());
        for(IBillRule rule:ruleList){
            System.out.println(rule.getRuleName());
        }
    }

}
