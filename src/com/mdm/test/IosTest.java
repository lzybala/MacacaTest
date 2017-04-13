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
//        JSONObject desiredCapabilities = new JSONObject();
//        desiredCapabilities.put("desiredCapabilities", porps);
//        desiredCapabilities.put("host", "10.1.11.173");
//        desiredCapabilities.put("port", "3456");
		driver.initDriver(porps);
	}
	
	
	@Test
	public void test_case_1() throws Exception {
		System.out.println("test case #1");
	}

	@Test
	public void test_case_2() throws Exception {
		System.out.println("test case #2");
	}

	@AfterSuite
	public void tearDown() throws Exception {
		driver.quit();
	}
}
