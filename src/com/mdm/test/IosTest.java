package com.mdm.test;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mdm.test.MacacaClientDesign.SwipeDirection;

import macaca.client.MacacaClient;
import macaca.client.common.GetElementWay;

public class IosTest {
	
	@BeforeSuite
	public void BeforeSuite() throws Exception {
	}
	
	
	@Test
	public void test_case_1() throws Exception {
		System.out.println("test case #1");
		BaseFunction.appBundleId = "com.apple.Preferences";
		BaseFunction.appName = "设置";
		BaseFunction.launchApp();
		MacacaClientDesign driver = BaseFunction.driver;
		
//		driver.swipeToUp();
//		driver.swipeToUp();
//		driver.swipeToUp();
//		driver.swipeToDown();
//		driver.swipeToDown();
//		driver.swipeToDown();
		
//		driver.backToHome();
//		driver.swipeToLeft();
//		driver.swipeToLeft();
//		driver.swipeToLeft();
//		driver.swipeToRight();
//		driver.swipeToRight();
//		driver.swipeToRight();
		
//		driver.elementToBeExist(GetElementWay.NAME, "通用", 5);
//		driver.elementByName("通用").click();
		
		
		
//		driver.elementToBeExistBySwipe(GetElementWay.NAME, "通用", SwipeDirection.UP, 5);
//		driver.elementByName("通用").click();
//		driver.backToHome();
	}

	@Test
	public void test_case_2() throws Exception {
		System.out.println("test case #2");
	
	}

	@AfterSuite
	public void tearDown() throws Exception {
		BaseFunction.quiteMacacaClient();
	}
}
