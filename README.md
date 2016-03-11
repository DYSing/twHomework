# TW-打印小票的小模块
---
## 版本 `V1.0` 
## 分支 `add-network-function`
### 提交时间：`2016年3月8日`
##### 功能说明：

增加分支`add-network-function`为程序提供网络功能，计划实现基于UDP或TCP协议的服务端程序，接收客户端发送数据并将结果返回的功能。

---
##版本 `V1.0`
##分支 `master`
###提交时间：`2016年3月5日 01:55:13 `
####开发环境：<i>JDK1.5 + Eclipse</i><br/>
#####功能说明：
第一个可以运行的版本。 实现“<b>打印小票的小模块，收银机会将输入的数据转换成一个JSON数据然后一次性传给我们这个小模块，我们将从控制台中输出结算清单的文本</b>”。<br/>
#####运行方法：
程序入口TW-payment/com/tw/billcontroller/Main.java。 输入参数写在
```java
getInputString()
```
方法中。
#####主要方法说明：
>TW-payment 项目主目录，实现输入输出控制及核心业务控制
>>com.tw.bill.rule
>>>BaseBillRule 在不加载或者加载优惠规则出错时实用的基本计费规则：单价*数量

>>com.tw.bill.util
>>>InputMessageHandle doInputMessage方法，处理输入参数

>>com.tw.billcontroller
>>>MyCore 加载并按照优先级调用优惠规则

>TW-billRules   为编写规则实现的项目

>TW-baseObject  主目录和规则目录公用的一些实体类以及规则接口

#####新优惠规则增加方法：
1. 增加优惠的具体实现类<br/>
1.1 在TW-billRules中新建一个类，继承TW-baseObject中的ExecutionRules类（ExecutionRules类是一个实现IBillRule接口的抽象类。ExecutionRules包含了具体优惠规则主要的逻辑） <br/>
1.2 实现将输入商品列表拆成涉及优惠和不涉及优惠两类的方法letGoodsToTwoListByDiscount(List goodsList,List discountGoods, List noDiscountGoods)<br/>
1.3 实现对拆分后需要进行优惠计算的商品进行计算的方法calculationDiscountGoods(List goodsList, GoodsBillWithPrice gbwp) <br/>
1.4 实现凭条打印时，优惠部分显示内容拼接的方法printDiscountMessage()<br/>

2.打包TW-baseObject成jar并放入TW-payment的rules文件夹中<br/>
3.根据注释，新增并编辑TW-payment的configs文件夹中的DiscountRules.xml文件里的内容

#####TODO： 
0.增加日志功能<br/>
1.增加程序运行过程中配置文件更新方法，实现不重启更新配置文件<br/>
2.增加从控制台获取输入并校验的功能<br/>
3.实现网络模式，业务在服务端，客户端发送购买的商品列表，接收打印凭条内容<br/>
4.网络模式中服务端多线程<br/>

---

##关于作者
```java
public static String uName="dongyi";
private static Integer age = ▇▇▇▇刮开看;
```
是的，我是一个程序
~~猿~~
员<br/>

<jiashuyao@163.com>