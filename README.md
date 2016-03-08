V1.0	2016年3月5日 01:55:13
基于JDK1.5
第一个可以运行的版本。
程序入口TW-payment/com/tw/billcontroller/Main.java。
输入参数写在getInputString()方法中。
项目分为3个目录
	TW-payment为主目录。
	TW-billRules为编写规则实现的项目。
	TW-baseObject是主目录和规则目录公用的一些实体类以及规则接口。

新优惠规则增加方法：
1. 增加优惠的具体实现类
1.1 在TW-billRules中新建一个类，继承TW-baseObject中的ExecutionRules类（ExecutionRules类是一个实现IBillRule接口的抽象类。ExecutionRules包含了具体优惠规则主要的逻辑）
1.2 实现将输入商品列表拆成涉及优惠和不涉及优惠两类的方法letGoodsToTwoListByDiscount(List<Goods> goodsList,List<Goods> discountGoods, List<Goods> noDiscountGoods) 
1.3 实现对拆分后需要进行优惠计算的商品进行计算的方法calculationDiscountGoods(List<Goods> goodsList, GoodsBillWithPrice gbwp)
1.4 实现凭条打印时，优惠部分显示内容拼接的方法printDiscountMessage()
2.打包TW-baseObject成jar并放入TW-payment的rules文件夹中
3.根据注释，新增并编辑TW-payment的configs文件夹中的DiscountRules.xml文件里的内容

TODO：
	0.增加日志功能
	1.增加程序运行过程中配置文件更新方法，实现不重启更新配置文件
	2.增加从控制台获取输入并校验的功能
	3.实现网络模式，业务在服务端，客户端发送购买的商品列表，接收打印凭条内容
	4.网络模式中服务端多线程