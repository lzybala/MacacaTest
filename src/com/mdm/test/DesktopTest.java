package com.mdm.test;

import macaca.client.MacacaClient;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class DesktopTest {
	
	MacacaClient driver = new MacacaClient();
 
	@BeforeSuite
	public void BeforeSuite() throws Exception {
		JSONObject porps = new JSONObject();
		porps.put("autoAcceptAlerts", true);
		porps.put("browserName", "electron");
		porps.put("platformName", "desktop"); // android or ios
		porps.put("javascriptEnabled", true);
		porps.put("platform", "ANY");
		//porps.put("app", "path/to/app");
		JSONObject desiredCapabilities = new JSONObject();
		desiredCapabilities.put("desiredCapabilities", porps);
		driver.initDriver(desiredCapabilities)
      .setWindowSize(1280, 800)
      .get("https://macacajs.github.io/");
	}
	
	
	@Test
	public void test_case_1() throws Exception {
		System.out.println("test case #1");
		driver.elementById("kw").sendKeys("macaca");
		driver.sleep(1000);
		driver.elementById("su").click();
		String html = driver.source();
		Assert.assertTrue(html.contains("macaca"));
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
