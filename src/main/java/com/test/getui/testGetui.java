package com.test.getui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.IQueryResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class testGetui {
	
	
		private static String appId = "iEytHLTuao5K76v7ZsLQ39";
	    private static String appKey = "Ote1D8kmvxARKnT4dBZk48";
	    private static String masterSecret = "USUrp2qTpy5hexDKUB83M";
	    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
	    public static void main(String[] args) throws IOException {
	        // 新建一个IGtPush实例，传入调用接口网址，appkey和masterSecret
	    	
	    	getUserTags();
	  
	    }
	    
	    
	    public static void testIos(){
	    	  IGtPush push = new IGtPush(url,appKey, masterSecret);  
	    	  TransmissionTemplate template = new TransmissionTemplate();
	    	  template.setAppId(appId);
	    	  template.setAppkey(appKey);
	    	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    	  template.setTransmissionType(2);
	    	  template.setTransmissionContent("请输入需要透传的内容");
	    	  APNPayload payload = new APNPayload();
	            payload.setBadge(1);
	            payload.setContentAvailable(1);
	            payload.setSound("default");
	            payload.setCategory("$由客户端定义");
	            payload.setAlertMsg(new APNPayload.SimpleAlertMsg(""));
	    	  AppMessage message = new AppMessage();
	    	  List appIdList = new ArrayList();
	    	  appIdList.add(appId);
	    	  message.setAppIdList(appIdList);
	    	  message.setData(template);
	        IPushResult ret0 = push.pushMessageToApp(message);
            System.out.println(ret0.getResponse());
	    	
	    }
	    
	    public static void TransmissionPush(){
	    	IIGtPush push = new IGtPush(url, appKey, masterSecret);  
	        try {  
	            AppMessage message = new AppMessage();  
	            TransmissionTemplate template = new TransmissionTemplate();
	            template.setAppId(appId);
	            template.setAppkey(appKey);
	            template.setTransmissionContent("透传内容");
	            template.setTransmissionType(2);
	            APNPayload payload = new APNPayload();
	            payload.setBadge(1);
	            payload.setContentAvailable(1);
	            payload.setSound("default");
	           // payload.setCategory("$由客户端定义");
	            payload.setAlertMsg(new APNPayload.SimpleAlertMsg("9:41"));
	            //字典模式使用下者
	            //payload.setAlertMsg(getDictionaryAlertMsg());
	            template.setAPNInfo(payload);
	            message.setData(template);  
	            message.setOffline(true);       //用户当前不在线时，是否离线存储，可选，默认不存储  
	            message.setOfflineExpireTime(72 * 3600 * 1000);     //离线有效时间，单位为毫秒，可选  
	            List<String> appIdList = new ArrayList<String>();  
	            appIdList.add(appId);  
	            List<String> phoneTypeList = new ArrayList<String>();//通知接收者的手机操作系统类型，可选  
	            phoneTypeList.add("IOS");  
	            List<String> tagList = new ArrayList<String>();//通知接收者的手机操作系统类型，可选  
	            tagList.add("HZSW");
	            AppConditions cdt = new AppConditions();         
	            cdt.addCondition(AppConditions.PHONE_TYPE, phoneTypeList);
	            cdt.addCondition(AppConditions.TAG, tagList);
	            message.setAppIdList(appIdList);    
	            message.setConditions(cdt);
	            IPushResult ret = push.pushMessageToApp(message);     
	            System.out.println(ret.getResponse().toString());  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }
	    
	    public static void tagNotificationPush(){
	    	
	    	 IGtPush push = new IGtPush(url,appKey, masterSecret);  
	    	AppMessage message = new AppMessage();
	    	NotificationTemplate template = new NotificationTemplate();
	        // 设置APPID与APPKEY
	        template.setAppId(appId);
	        template.setAppkey(appKey);
	        // 设置通知栏标题与内容
	        template.setTitle("请输入通知栏标题");
	        template.setText("请输入通知栏内容");
	        // 设置通知是否响铃，震动，或者可清除
	        template.setIsRing(true);
	        template.setIsVibrate(true);
	        template.setIsClearable(true);
	        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	        //template.setTransmissionType(1);
	        template.setTransmissionContent("请输入您要透传的内容");
	        APNPayload payload = new APNPayload();
            payload.setBadge(1);
            payload.setContentAvailable(1);
            payload.setSound("default");
           // payload.setCategory("$由客户端定义");
            payload.setAlertMsg(new APNPayload.SimpleAlertMsg("9:41"));
            template.setAPNInfo(payload);
	        List<String> appIdList=new ArrayList<String>();
	        List<String> tagList=new ArrayList<String>();
	        appIdList.add(appId);
	        tagList.add("HZSW");
	        message.setAppIdList(appIdList);
	        AppConditions cdt = new AppConditions();
	        cdt.addCondition(AppConditions.TAG, tagList);
	        message.setConditions(cdt);
	        message.setData(template);
	        IPushResult ret = push.pushMessageToApp(message);
	        System.out.println(ret.getResponse().toString());
	    	
	    }
	    
	    
	    public static void pushSingleToIOS(){
	    	
	    	 IGtPush push = new IGtPush(url,appKey, masterSecret);  
		    	NotificationTemplate template = new NotificationTemplate();
		        // 设置APPID与APPKEY
		        template.setAppId(appId);
		        template.setAppkey(appKey);
		        // 设置通知栏标题与内容
		        template.setTitle("请输入通知栏标题");
		        template.setText("请输入通知栏内容");
		        // 设置通知是否响铃，震动，或者可清除
		        template.setIsRing(true);
		        template.setIsVibrate(true);
		        template.setIsClearable(true);
		        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		        template.setTransmissionType(1);
		        template.setTransmissionContent("请输入您要透传的内容");
		        APNPayload payload = new APNPayload();
	            payload.setBadge(1);
	            payload.setContentAvailable(1);
	            payload.setSound("default");
	           // payload.setCategory("$由客户端定义");
	            payload.setAlertMsg(new APNPayload.SimpleAlertMsg("9:41"));
	            template.setAPNInfo(payload);
	    	  SingleMessage message = new SingleMessage();
	          message.setOffline(true);
	          //离线有效时间，单位为毫秒，可选
	          message.setOfflineExpireTime(24 * 3600 * 1000);
	          message.setData(template);
	         // message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
	          Target target = new Target();
	          target.setAppId(appId);
	          target.setClientId("3d04b4ca55fc6c3790447adc3e7538a8");
	          //用户别名推送，cid和用户别名只能2者选其一
	          //String alias = "个";
	          //target.setAlias(alias);
	          IPushResult ret = null;
	          try{
	              ret = push.pushMessageToSingle(message, target);
	          }catch(RequestException e){
	              e.printStackTrace();
	              ret = push.pushMessageToSingle(message, target, e.getRequestId());
	          }
	          if(ret != null){
	              System.out.println(ret.getResponse().toString());
	          }else{
	              System.out.println("服务器响应异常");
	      }
	    	
	    	
	    	
	    	
	    }
	    
	    public static void pushSingletoAndroid(){
	    	 IGtPush push = new IGtPush(url,appKey, masterSecret);  
	    	NotificationTemplate template = new NotificationTemplate();
	        // 设置APPID与APPKEY
	        template.setAppId(appId);
	        template.setAppkey(appKey);
	        // 设置通知栏标题与内容
	        template.setTitle("请输入通知栏标题");
	        template.setText("请输入通知栏内容");
	        // 设置通知是否响铃，震动，或者可清除
	        template.setIsRing(true);
	        template.setIsVibrate(true);
	        template.setIsClearable(true);
	       // template.setTransmissionType(2);
	        template.setTransmissionContent("{type:-1}");
	    	
	        SingleMessage message = new SingleMessage();
	          message.setOffline(true);
	          //离线有效时间，单位为毫秒，可选
	          message.setOfflineExpireTime(24 * 3600 * 1000);
	          message.setData(template);
	          message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
	          Target target = new Target();
	          target.setAppId(appId);
	          target.setClientId("d2fffc86aca962b337319c81e2c896ec");
	          //用户别名推送，cid和用户别名只能2者选其一
	          //String alias = "个";
	          //target.setAlias(alias);
	          IPushResult ret = null;
	          try{
	              ret = push.pushMessageToSingle(message, target);
	          }catch(RequestException e){
	              e.printStackTrace();
	              ret = push.pushMessageToSingle(message, target, e.getRequestId());
	          }
	          if(ret != null){
	              System.out.println(ret.getResponse().toString());
	          }else{
	              System.out.println("服务器响应异常");
	      }
	    	
	    	
	    }
	    
	    public static void checkResult(){
	    	
	    	 IGtPush push = new IGtPush(url,appKey, masterSecret);  
             IPushResult result=push.getPushResult("OSS-0523_e5b1d4dda52dec4add0ad8a1b4b947bc");
             String msgTotal =result.getResponse().get("msgTotal").toString();
             String clickNum=result.getResponse().get("clickNum").toString();
             String msgProcess =result.getResponse().get("msgProcess").toString();
             
             System.out.println("总下发数:"+msgTotal+"|点击数:"+clickNum+"|下发的消息总数:"+msgProcess);
	    }
	    
	    
	    
	    public static void getUserTags() {
	       IGtPush push = new IGtPush(url,appKey, masterSecret);  
	       IPushResult result = push.getUserTags(appId, "16390de538df8ef164c4c34a387f6392");
	       String j=(String)result.getResponse().get("tags");
	       String tags[]=j.split(" ");
	       for(String s:tags){
	    	   System.out.println(s);
	       }
	    }
	    
	    public static void getTagUser(){
	    	 IGtPush push = new IGtPush(url,appKey, masterSecret);  
	    	  AppConditions conditions = new AppConditions();
	    	  List<String> tags = new ArrayList<String>();
	          tags.add("39570");
	          conditions.addCondition(AppConditions.TAG, tags);
	    	 IQueryResult  result = push.queryUserCount(appId, conditions);
	    	 System.out.println(result.getResponse());
	    	
	    	
	    }
	    	
	    	
	    }

