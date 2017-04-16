package com.mdm.test;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;



import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mdm.test.MacacaClientDesign.SwipeDirection;
import macaca.client.common.ElementSelector;
import macaca.client.MacacaClient;
import macaca.client.common.GetElementWay;

public class IosTest {
	
	public MacacaClientDesign driver = BaseFunction.initMacacaClient(false);
	
	@BeforeSuite
	public void BeforeSuite() throws Exception {
		driver.isElementExist(GetElementWay.NAME, "设置");
	}
	
	@Test 
	public void testSwipe() throws Exception{
		Integer windownSizeWidth = driver.windownSizeWidth;
		Integer windownSizeHeight = driver.windownSizeHeight;
		driver.swipe(windownSizeWidth/2, windownSizeHeight/5*4, windownSizeWidth/2, windownSizeHeight/5*1, 0.5);
		
		driver.swipeToUp();
		driver.swipeToUp();
		driver.swipeToUp();
		driver.swipeToDown();
		driver.swipeToDown();
		driver.swipeToDown();

		driver.backToHome();
		driver.swipeToLeft();
		driver.swipeToLeft();
		driver.swipeToLeft();
		driver.swipeToRight();
		driver.swipeToRight();
		driver.swipeToRight();
	}
	
	@Test
	public void testGetSubElements() throws Exception{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("using", "name");
		jsonObject.put("value", "杨亚霖");
		ElementSelector elements = driver.findChildElements(driver.elementsByClassName("XCUIElementTypeCell").getIndex(0), GetElementWay.NAME, "杨亚霖");
		System.out.println("----" +elements.size() + " ==== " + elements);
		JSONArray elementsName = driver.elementsByClassName("XCUIElementTypeCell").getIndex(0).findChildElements(GetElementWay.NAME, "杨亚霖");
		System.out.println("----" +elementsName.size() + " ==== " + elementsName);
		JSONArray elementsClass = driver.elementsByClassName("XCUIElementTypeCell").getIndex(1).findChildElements(GetElementWay.CLASS_NAME, "XCUIElementTypeSwitch");
		System.out.println("----" +elementsClass.size() + " ==== " + elementsClass);
	}
	
	@Test
	public void testWaitElementAppear() throws Exception {
		Assert.assertTrue(driver.elementToBeExist(GetElementWay.NAME, "通用", 5));
		driver.elementByName("通用").click();
	}
	
	@Test
	public void testSwipeToFindElement() throws Exception {
		driver.elementToBeExistBySwipe(GetElementWay.NAME, "hahah", SwipeDirection.UP, 5);
		driver.elementByName("hahah").click();
	}

	@Test
	public void testRelaunchApp() throws Exception{
//		driver.elementByName("通知").click();
//		driver.elementByName("App Store").click();
//		driver.backToHome();
//		
//		BaseFunction.appBundleId = "com.nq.mdm";
//		BaseFunction.appName = "NQSky EMM";
//		BaseFunction.launchApp();
//		
//		BaseFunction.appBundleId = "com.apple.Preferences";
//		BaseFunction.appName = "设置";
//		BaseFunction.launchApp();
	}

	@AfterSuite
	public void tearDown() throws Exception {
		BaseFunction.quiteMacacaClient();
	}
}
