package com.tw.bill.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.tw.bill.RuleInfo;

public class RulesSaxParseService extends DefaultHandler {
	  private List<RuleInfo> rulesList = null;  
	    private RuleInfo rules = null;  
	    private String preTag = null;//作用是记录解析时的上一个节点名称  
	      
	    
	    
	    
	    public List<RuleInfo> getRules(String  uri) throws Exception{  
	        SAXParserFactory factory = SAXParserFactory.newInstance();  
	        SAXParser parser = factory.newSAXParser();  
	        RulesSaxParseService handler = new RulesSaxParseService();  
	        parser.parse(uri, handler);  
	        return handler.getRules();  
	    }  
	      
	    public List<RuleInfo> getRules(){  
	        return rulesList;  
	    }  
	      
	    @Override  
	    public void startDocument() throws SAXException {  
	    	rulesList = new ArrayList<RuleInfo>();  
	    }  
	  
	    @Override  
	    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {  
	        if("rule".equals(qName)){  
	        	rules = new RuleInfo();  
	        }  
	        preTag = qName;
	    }  
	  
	    @Override  
	    public void endElement(String uri, String localName, String qName)  
	            throws SAXException {  
	        if("rule".equals(qName)){  
	        	rulesList.add(rules);  
	        	rules = null;  
	        }  
	        preTag = null;
	    }  

	    @Override  
	    public void characters(char[] ch, int start, int length) throws SAXException {  
	        if(preTag!=null){  
	            String content = new String(ch,start,length);  
	            if("name".equals(preTag)){  
	            	rules.setRuleName(content);  
	            }else if("jarFileName".equals(preTag)){  
	            	rules.setJarFileName(content);  
	            }
	            else if("jarFilePath".equals(preTag)){  
	            	rules.setJarFilePath(content);  
	            } 
	            else if("className".equals(preTag)){  
	            	rules.setClassName(content);  
	            }  
	            else if("level".equals(preTag)){  
	            	rules.setLevel(Integer.parseInt(content));  
	            }  else if("goods".equals(preTag)){  
	            	rules.setRULS(content.split("#"));  
	            }  
	        }  
	    }  
}
