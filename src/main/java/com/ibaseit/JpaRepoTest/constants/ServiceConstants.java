
package com.ibaseit.JpaRepoTest.constants;

public class ServiceConstants {
	
	
	/*****************************************
	 * Messages for HTTP Status codes
	 *****************************************/
	public static final String MESSAGE_401 = "You are not authorized to view the resource";
	public static final String MESSAGE_403 = "Accessing the resource you were trying to reach is forbidden";
	public static final String MESSAGE_404 = "The resource you were trying to reach is not found";

	/*****************************************
	 * Resource Info
	 *****************************************/
	public static final String APP_TITLE = "Cortex Service";
	public static final String APP_DESCRIPTION = "";
	public static final String APP_VERSION = "";
	public static final String TERMS_OF_SERVICE_URL = "";
	public static final String CONTACT_NAME = "";
	public static final String CONTACT_URL = "";
	public static final String CONTACT_EMAIL = "";
	public static final String APP_LICENSE = "";

	/*****************************************
	 * Packages
	 *****************************************/
	public static final String CONTROLLER_PACKAGE = "com.cortex.controller";
	
	
	/*****************************************
	 * Pagination
	 *****************************************/
	
	

	/*****************************************
	 *  Restful Endpoints
	 *****************************************/
	public static final String FORB2C_CONTROLLER_PATH = "/api";
	public static final String FIND_QUOTE = "/findquote";
	public static final String QUOTE = "/quote";
	public static final String TAKEUP_QUOTE = "/takeupquote";
	public static final String LOADING_QUOTE = "/loadingQuote";
	public static final String LOAD_QUOTE = "/loadQuote";
	public static final String PRINT_QUOTE = "/printQuote";
	public static final String PRINT_POLICY = "/printPolicy";
	public static final String PRINT_POLICY_DANNI = "/printPolicy";
	
	public static final String PRINT_QUOTE_DANNI = "/printQuote";
	public static final String POLICY_PAYMENT_UPDATE = "/policypaymentupdate";
	public static final String AMEND_CUSTOMER_DETAILS = "/amendCustomerDetails";
	public static final String AMEND_QUOTE = "/amendQuote";
	public static final String AMEND_PRICE_DETAILS = "/amendPrice";
	public static final String AMEND_MILEAGE_DETAILS = "/amendMileage";
	public static final String AMEND_QUESTIONARIES = "/amendQuestionaries";
	
	/*****************************************
	 *  Messages
	 *****************************************/
	public static final String SUCCESS_MSG = "Success";
	
	
	/*****************************************
	 *  Steps
	 *****************************************/
	

	public static final String PASSPHRASE="DANNI-FIRSTBASEIT";

	public static final String STEP_FINDQUOTE="find@quote";

	public static final String STEP_AMENDCUSTOMER="customer";
	
	public static final String STEP_AMENDMILEAGE="mileage";
	
	public static final String STEP_AMENDPRICING="claimexcess";
	
	public static final String STEP_AMENDQUESTIONARIES="questions";
	
	public static final String STEP_TAKEUPQUOTE="takeup@quote";
	
	public static final String STEP_LOADINGQUOTE="loading@quote";
	
	public static final String STEP_PRINTQUOTE="print@quote";
	
	public static final String STEP_POLICY_PAYMENT_UPDATE="policy@paymentupdate";
	
	
	/*****************************************
	 *  ShaSign
	 *****************************************/
	public static String LANGUAGE = "en_US";
	public static String CURRENCY = "GBP";
	public static String BGCOLOR = "#ffffff";
	public static String BUTTONBGCOLOR = "#2d96cd";
	public static String BUTTONTXTCOLOR = "#ffffff";
	public static String HDFONTTYPE = "Verdana";
	public static String HDTBLBGCOLOR = "#005a9b";
	public static String HDTBLTXTCOLOR = "#ffffff";
	public static String TBLBGCOLOR = "#dfe1df";
	public static String TBLTXTCOLOR = "#1b394e";
	public static String TITLE = "Ford Protect Warranty Payment";
	public static String TP = "PaymentPage_1_iPhone.htm";
	public static String OPERATION = "RES";
	public static String SUB_COM = "FBFM";
	public static String SUB_COMMENT = "NEWSUBSCRIPTION";
	public static String SUB_PERIOD_NUMBER = "1";
	public static String SUB_PERIOD_UNIT = "m";
	public static String SUB_STATUS = "1";
	public static String TXTCOLOR = "#1b394e";
	
	/**
	 * util constants
	 */
/*	public static final String SCHEMA_PATH = "classpath:config/validation_schema.json";
	public static final String FAILED = "failed";
	public static final String FAILURE_RESP = "inValid json";
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_CODE_417 = "417";
	public static final String ERROR_CODE_500 = "500";
	public static final String ERROR_MSG = "errorMsg";
	public static final String SUCCESS = "success";
	public static final String SUCCESS_CODE = "200";
	public static final String SUCCESS_RESP = "json is valid";
	public static final String RESULT = "result ";*/
	/**
	 * Data Handler constants
	 */
	public static final String DATE_FORMAT="MMM d, yyyy hh:mm:ss a";
	public static final String DATE_FORMAT2="yyyy-MM-dd hh:mm:ss";
	
}
