package com.mdm.test;

import org.testng.Assert;
import com.alibaba.fastjson.JSONObject;
import macaca.client.MacacaClient;
import macaca.client.commands.Element;
import macaca.client.common.GetElementWay;

public class MacacaClientDesign extends MacacaClient{
	
	// windownSizeWidth
	public Integer windownSizeWidth = 0;
	// windownSizeHeight
	public Integer windownSizeHeight = 0;
	
	/**
	 * 滑动方向
	 */
	public enum SwipeDirection {
		UP, DOWN, LEFT, RIGHT;
	}
	
	/**
	 * initDriver时获取windowSize
	 */
	@Override
	public MacacaClient initDriver(JSONObject jsonObject) throws Exception {
		 super.initDriver(jsonObject);
		 JSONObject PhoneSize = super.getWindowSize();
		 windownSizeWidth = PhoneSize.getInteger("width");
		 windownSizeHeight = PhoneSize.getInteger("height");
		 return this;
	}
	
	/**
	 * 滑动界面
	 * @throws Exception 
	 */
	public void swipe(double fromX, double fromY, double toX, double toY, double duration) throws Exception {
		JSONObject params = new JSONObject();
		params.put("fromX", fromX);
		params.put("fromY", fromY);
		params.put("toX", toX);
		params.put("toY", toY);
		params.put("duration", duration);
		super.touch("drag", params);
	}
	
	/**
	 * 向上滑动界面
	 * @throws Exception 
	 */
	public void swipeToUp() throws Exception {
		swipe(windownSizeWidth/2, windownSizeHeight/5*4, windownSizeWidth/2, windownSizeHeight/5*1, 0.5);
	}
	
	/**
	 * 向下滑动界面
	 * @throws Exception
	 */
	public void swipeToDown() throws Exception {
		swipe(windownSizeWidth/2, windownSizeHeight/5*1, windownSizeWidth/2, windownSizeHeight/5*4, 0.5);
	}
	
	/**
	 * 向左滑动界面
	 * @throws Exception 
	 */
	public void swipeToLeft() throws Exception {
		swipe(windownSizeWidth/10*9, windownSizeHeight/2, windownSizeWidth/10*1, windownSizeHeight/2, 0.5);
	}
	
	/**
	 * 向右滑动界面
	 * @throws Exception 
	 */
	public void swipeToRight() throws Exception {
		swipe(windownSizeWidth/10*1, windownSizeHeight/2, windownSizeWidth/10*9, windownSizeHeight/2, 0.5);
	}
	
	/**
	 * 滑动查找元素
	 * @param wayToFind	元素查找方式
	 * @param value	元素查找值
	 * @param direction	滑动方向
	 * @param maxSwipeCount 最大滑动次数
	 * @return	true: 找到元素 false: 未找到元素
	 */
	public boolean elementToBeExistBySwipe(GetElementWay wayToFind, String value, SwipeDirection direction, int maxSwipeCount) {
		boolean isElementAppear = false;
		try {
			while (maxSwipeCount > 0) {
				isElementAppear = elementToBeExist(wayToFind, value, 1);
				if (isElementAppear) {
					break;
				}
				switch (direction) {
				case UP:
					swipeToUp();
				case DOWN:
					swipeToDown();
				case LEFT:
					swipeToLeft();
				case RIGHT:
					swipeToRight();
				default:
					break;
				}
				maxSwipeCount--;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isElementAppear = false;
			Assert.fail(e.getMessage());
		} 
		return isElementAppear;
	}
	
	/**
	 * 等待元素出现
	 * @param wayToFind	元素查找方式
	 * @param value	元素查找值
	 * @param timeout	等待元素出现超时时间，超过等待时间跳过
	 * @return
	 */
	public boolean elementToBeExist(GetElementWay wayToFind, String value, int timeout){
		boolean flag = false;
		while(timeout>0 && !flag){
			try {
				flag = super.isElementExist(wayToFind, value);
				super.sleep(1000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				flag = false;
			}
			timeout--;
		}
		return flag;
	}
	

	@Override
	public Element elementByName(String name) throws Exception {
		// TODO Auto-generated method stub
		Element element = (Element)super.elementByName(name);
		Assert.assertNotNull(element, "找不到元素: [" +name+"]");
		return element;
	}

	@Override
	public Element elementByXPath(String xpath) throws Exception {
		// TODO Auto-generated method stub
		Element element = (Element)super.elementByXPath(xpath);
		Assert.assertNotNull(element, "找不到元素: [" +xpath+"]");
		return element;
	}
	
	
	
//	@Override
//	public MacacaClient waitForElement(String using, String value) throws Exception {
//		// default timeout:2000, default interval:200
//		return waitForElement(using, value, waitElementTimeout, waitElementTimeInterval);
//	}
//	
//	@Override
//	public JSONObject getWindowSize() throws Exception {
//		// TODO Auto-generated method stub
//		JSONObject size = super.getWindowSize();
//		int i = 5;
//		while(size.isEmpty() && i>0){
//			size = super.getWindowSize();
//			Thread.sleep(500);
//			i--;
//		}
//		if(size.isEmpty() && i == 0)
//			Assert.fail("获取手机屏幕Size为空！");
//		
//		return size;
//	}
//
//	@Override
//	public MacacaClient waitForElement(String using,String value,int timeout,int interval) throws Exception {
//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("value", value);
//		jsonObject.put("using", using);
//		int count = 1;
//		int timeLeft = timeout;
//		boolean satisfied = false;
//		while (timeLeft > 0) {
//			boolean elementExist = false;
//			System.out.println(String.format("attempt to search the element for %d times", count++));
//			elementExist = this.isElementExist(using, value);
//			if (!elementExist) {
//				// not find element ,keep searching
//				this.sleep(interval);
//				timeLeft -= interval;
//			} else {
//				// finded , break
//				satisfied = true;
//				break;
//			}
//		}
//		if (satisfied == false) {
//			System.out.println("can't find the element: " + using + ":" + value);
//			return null;
//		}
//		
//		return this;
//	}
	
}