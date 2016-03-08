package com.tw.bill.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tw.bill.Goods;
import com.tw.bill.constant.BillConstant.GoodsType;

public class GoodsSaxParseService extends DefaultHandler {
	  private List<Goods> goodsList = null;  
	    private Goods goods = null;  
	    private String preTag = null;//作用是记录解析时的上一个节点名称  
	      
	    
	    
	    
	    public List<Goods> getGoods(String  uri) throws Exception{  
	        SAXParserFactory factory = SAXParserFactory.newInstance();  
	        SAXParser parser = factory.newSAXParser();  
	        GoodsSaxParseService handler = new GoodsSaxParseService();  
	        parser.parse(uri, handler);  
	        return handler.getGoods();  
	    }  
	      
	    public List<Goods> getGoods(){  
	        return goodsList;  
	    }  
	      
	    @Override  
	    public void startDocument() throws SAXException {  
	        goodsList = new ArrayList<Goods>();  
	    }  
	  
	    @Override  
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	        if("goods".equals(qName)){  
	            goods = new Goods();  
	            goods.setgId(attributes.getValue("id"));  
	        }  
	        preTag = qName;
	    }  
	  
	    @Override  
	    public void endElement(String uri, String localName, String qName)  
	            throws SAXException {  
	        if("goods".equals(qName)){  
	            goodsList.add(goods);  
	            goods = null;  
	        }  
	        preTag = null;
	    }  
	    public GoodsType getGoodsType(String content){
	    	if("个".equals(content)){
	    		return GoodsType.G;
	    	}else if("斤".equals(content)){
	    		return GoodsType.J;
	    	}else if("瓶".equals(content)){
	    		return GoodsType.P;
	    	}
	    	return GoodsType.G;
	    }
	    @Override  
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	        if(preTag!=null){  
	            String content = new String(ch,start,length);  
	            if("name".equals(preTag)){  
	                goods.setgName(content);  
	            }else if("type".equals(preTag)){  
	                goods.setgType(getGoodsType(content));  
	            }
	            else if("price".equals(preTag)){  
	                goods.setgPrice(Float.parseFloat(content));  
	            }  
	        }  
	    }  
}
