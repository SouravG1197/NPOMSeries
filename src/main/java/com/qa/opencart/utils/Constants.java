package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static  final int DEFAULT_TIME_OUT=5;
	
	public static  final String LOGIN_PAGE_TITLE="Login - My Shop";
	public static  final String ACC_PAGE_TITLE="My account - My Shop";
	public static  final String ACC_PAGE_HEADER="MY ACCOUNT";
	public static  final int ACC_PAGE_SECTIONS_COUNT=5;
	public static  final int PRODUCT_IMAGE_COUNT=5;
	public static final String REGISTER_SUCESS_MESSG="Your account has been created.";
	
	
	public static  final String REGISTER_SHEET_NAME="sheet1";
	
	
	
	public static final List<String>EXPECTED_ACC_SECS_LIST
	            =Arrays.asList("ADD MY FIRST ADDRESS",
	            		"ORDER HISTORY AND DETAILS",
	            		"MY CREDIT SLIPS",
	            		"MY ADDRESSES",
	            		"MY PERSONAL INFORMATION");

}
