package com.shravan.utilities;

public class TestConfig{


	
	public static String server = "smtp.gmail.com";
	public static String from = "bingoforselenium@gmail.com";
	public static String password = "Opentext1!";
	public static String[] to = { "shravan9119@gmail.com", "schintha@opentext.com" };
	public static String subject = "Selenium Extent Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="f:\\screenshot\\error.jpg";
	public static String attachmentName="error.png";
	
	
	
	//SQL DATABASE DETAILS	
	public static String driver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.cj.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "selenium";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/24thmay2020";
	
	
	
	
	
	
	
	
	
}
