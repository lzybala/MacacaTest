package com.mdm.test;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import macaca.client.MacacaClient;

public class IosTest {
	MacacaClient driver = new MacacaClient();
	 
	@BeforeSuite
	public void BeforeSuite() throws Exception {
		JSONObject porps = new JSONObject();
		porps.put("platformName", "ios");
		porps.put("deviceName", "iPhone");
		porps.put("platformVersion", "10.3.1");
		porps.put("udid", "45feaea59d2908c6c1457469cf692a0ff71bfaf1");
		porps.put("bundleId", "com.nq.mdm");
		porps.put("autoAcceptAlerts", "true");
		JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("host", "192.168.1.9");
        desiredCapabilities.put("port", 3456);
        desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities);
	}
	
	
	@Test
	public void test_case_1() throws Exception {
		System.out.println("test case #1");
		
		driver.elementByName("服务器地址").sendKeys("172.16.10.218");
//		driver.elementByName("企业标识码").sendKeys("mdm");
//		driver.elementByName("用户名").sendKeys("lzy");
//		driver.elementByName("激活密码").sendKeys("123456");
//		driver.elementByName("Done").click();
//		driver.elementByName("激活").click();
//		driver.sleep(10000);
//		driver.back();
//		driver.sleep(3000);
	}

	@Test
	public void test_case_2() throws Exception {
		System.out.println("test case #2");
		
		if (driver.sessionId() != null) 
			driver.quit();
		
		JSONObject porps = new JSONObject();
		porps.put("platformName", "ios");
		porps.put("deviceName", "iPhone");
		porps.put("platformVersion", "10.3.1");
		porps.put("udid", "45feaea59d2908c6c1457469cf692a0ff71bfaf1");
		porps.put("bundleId", "com.apple.Preferences");
		porps.put("autoAcceptAlerts", "true");
		JSONObject desiredCapabilities = new JSONObject();
        desiredCapabilities.put("host", "192.168.1.9");
        desiredCapabilities.put("port", 3456);
        desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities);
		
		
	}

	@AfterSuite
	public void tearDown() throws Exception {
//		driver.quit();
	}
}
