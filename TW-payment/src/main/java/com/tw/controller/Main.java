package com.tw.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {

    /**
     * 程序从此开始
     * 通过输入购买的商品列表生成最终的凭条
     *
     * @param args 暂不使用
     */
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MainController mainController = (MainController) context.getBean("mainController");
        mainController.run();
    }


}
