package com.bfd.mf.monitor.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: chengwei.wang
 * @Description: 个人测试页面，容易癫狂，为了您和家人的健康，请勿动。
 * @Date: Created in 18:10 2017/11/9
 * @Modified_By:
 */
public
class String_test {
	private static java.text.DecimalFormat rateIllustate = new java.text.DecimalFormat("###.00");
	private static java.text.DecimalFormat testIllustate = new java.text.DecimalFormat("0.##");

	public static void main(String[] args){

//		String test = " ( d05=2 or d05=1) and ksjaiw  and lkajw and   and      klwjai  ";
//
//		String newTest = deep(test);
//		System.out.println(newTest);
		double x = 92.3;
		double y = 100d-x;
		System.out.println(rateIllustate.format(y));
		double test = 0.1000004;
		double test2 = 3.100000;
		System.out.println(testIllustate.format(test));
		System.out.println(testIllustate.format(test2));

		Map<String, Object> map = new HashMap<String, Object>();
		if (map == null) {
			System.out.println("null map");
		}
		List<Map<String,Object>> tasks=new ArrayList<Map<String,Object>>();
		int xx = tasks.size();
		System.out.println(xx);

	}

	public static String deep(String test){
		test = test.trim();
		int index = test.indexOf("d05");
		int indexAfter;
		int lenth = 0;
		String pretest = "";
		String afttest = "";
		if(index < 0){
			return " " + test + " ";
		}
		if(index > 0) {
			//找到了d05
			pretest = test.substring(0, index);
			pretest = pretest.trim();
			lenth = pretest.length();
			if(lenth>0 && pretest.substring(lenth-1,lenth).equals("(")){
				pretest = pretest.substring(0,lenth-1).trim();
			}
			lenth = pretest.length();
			if(lenth>0 &&pretest.substring(lenth-3,lenth).equals("and")){
				pretest = pretest.substring(0,lenth-3);
			}
			//System.out.println("this is pretest " + pretest);

		}
			// d05 以前字符串已经处理，下面是以后的
			indexAfter = test.indexOf(")",index + 3);

		//indexAfter 不可能等于0
		if(indexAfter > 0){
			afttest = test.substring(indexAfter+1, test.length()).trim();
		}
		lenth = afttest.length();
		System.out.println("afttest----" + afttest);
		if(lenth>0&&afttest.substring(0,3).equals("and")) {

				 afttest = afttest.substring(3, lenth);

		}
		System.out.println(pretest + " " + afttest);
		return deep(pretest + " " + afttest);

	}
}
