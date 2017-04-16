package com.mdm.test;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.mdm.test.MacacaClientDesign.SwipeDirection;

import macaca.client.common.GetElementWay;

public class BaseFunction{
	
	public static MacacaClientDesign driver = null;	// MacacaClient
	public static String deviceName = "iPhone";
	public static String deviceUdid = "45feaea59d2908c6c1457469cf692a0ff71bfaf1";
	public static String devicePlatformVersion = "10.3.1";
	public static String appBundleId = "com.apple.Preferences";	// 测试应用的BundleId
	public static String appName = "设置"; // 测试应用的名称
	public static String host = "localhost";
	public static Integer port = 3456;
    private static final String screenshortDir =  System.getProperty("user.dir") + "/screenshot/"; 
	
    /**
     * 初始化MacacaClient，打开appBundleId对于的应用
     * @param autoAcceptAlert	是否自动接受弹框，默认false
     * @return
     */
	public static MacacaClientDesign initMacacaClient(Boolean autoAcceptAlert){
		JSONObject porps = new JSONObject();
        porps.put("platformName", "ios");
        porps.put("platformVersion", devicePlatformVersion);
        porps.put("deviceName", deviceName);
        porps.put("udid", deviceUdid);
        porps.put("bundleId", appBundleId);
        porps.put("autoAcceptAlerts", autoAcceptAlert.toString());
        porps.put("reuse", "2");
        JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("desiredCapabilities", porps);
        desiredCapabilities.put("host", host);
        desiredCapabilities.put("port", port);
        quiteMacacaClient();
	    try {
	    	driver = new MacacaClientDesign();
    		driver.initDriver(desiredCapabilities);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("IosDriver初始化失败");
		}
	    driver.setWaitElementTimeout(6000);
	    driver.setWaitElementTimeInterval(500);
	    return driver;
    }
	
	/**
	 * MacacaClient退出，断开连接
	 */
	public static void quiteMacacaClient(){
		try {
			if(driver != null && !driver.sessionId().isEmpty()){
				driver.sleep(3000).quit();
				System.out.println("--------Macaca Driver Quited--------");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 重新加载Mdm，如果Mdm是在后台运行状态，会退出当前运行状态重新加载
	 */
	public static void launchApp() {
		initMacacaClient(false);
	}
	
	/**
	 * 重新加载Mdm，如果Mdm是在后台运行状态，会退出当前运行状态重新加载
	 */
	public static void relauchMdm(boolean autoAcceptAlert) {
		initMacacaClient(autoAcceptAlert);
	}
	
	/**
	 * 从主屏幕打开MDM，打开时保持上次退出的状态
	 */
	public static void openApp() {
		try{
			driver.backToHome();
			if (driver.elementToBeExistBySwipe(GetElementWay.NAME, appName, SwipeDirection.LEFT, 5)) {
				driver.elementByName(appName).click();
			}
		}catch(Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage(), e.getCause());
		}
	}
	
	/**
	 * 屏幕截图
	 * @param screenName
	 */
	public static void takeScreenShot(String screenName){
		try {
			Date currentTime = new Date();
			DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String timeString = formatter.format(currentTime);
			screenName = screenName + "_" + timeString + ".jpg";
			screenName = screenshortDir + screenName;
			driver.saveScreenshot(screenName);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	/**
//	 * 向上滑动查找元素
//	 * @param elementName
//	 */
//	public static void scrollUpToElementByName(String elementName) {
//		try {
//			while (toBeExistByName(elementName,1) != true) {
//				int width = PhoneSize.getInteger("width");
//				int height = PhoneSize.getInteger("height");
//				driver.swipe(width/2, height/5*4, width/2, height/5*1, 10);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		} 
//	}
//	
//	/**
//	 * 向下滑动查找元素
//	 * @param elementName
//	 */
//	public static void scrollDownToElementByName(String elementName) {
//		try {
//			while (toBeExistByName(elementName,1) != true) {
//				int width = PhoneSize.getInteger("width");
//				int height = PhoneSize.getInteger("height");
//				driver.swipe(width/2, height/5*1, width/2, height/5*4, 10);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail(e.getMessage());
//		} 
//	}
//	
//	/**
//	 * 等待元素出现
//	 * @param name
//	 * @param timeout
//	 * @return
//	 */
//	public static boolean toBeExistByName(String name, int timeout){
//		boolean flag = false;
//		while(timeout>0 && !flag){
//			try {
//				flag = driver.isElementExist("name", name);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				// e.printStackTrace();
//				flag = false;
//			}
//			delay(0.5);
//			timeout--;
//		}
//		return flag;
//	}
//	
//	/**
//	 * 等待元素出现
//	 * @param xpath
//	 * @param timeout
//	 * @return
//	 */
//	public static boolean toBeExistByXpath(String xpath, int timeout){
//		boolean flag = false;
//		while(timeout>0 && !flag){
//			try {
//				flag = driver.isElementExist("xpath", xpath);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				// e.printStackTrace();
//				flag = false;
//			}
//			delay(1);
//			timeout--;
//		}
//		return flag;
//	}
//	
//	/**
//	 *  判断元素是否存在
//	 */
//	public static boolean isElementExistByName(String name){
//		boolean result = false;
//		try {
//			result = driver.isElementExist("name", name);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			result = false;
//		}
//		return result;
//	}
//	
//	/**
//	 *  判断元素是否存在
//	 */
//	public static boolean isElementExistByXPath(String xpath){
//		boolean result = false;
//		try {
//			result = driver.isElementExist("xpath", xpath);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			// e.printStackTrace();
//			result = false;
//		}
//		return result;
//	}
	
	public static void delay(double i) {
		try {
			System.out.println("delay:" + i + "s");
			Thread.sleep((int)(i * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
