package com.dlg.projectmodule.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 字符串时间转Datel类型
	 * @param format：字符串时间格式
	 * @param dateString：字符串时间
	 * @return Date
	 */
	public static Date dateString2DateObject(String format, String dateString) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date timestamp = df.parse(dateString);
			return timestamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/**
	 * 字符串时间转long(ms)
	 * @param format: 原始字符串时间格式
	 * @param dateString：字符串时间
	 * @return long
	 */
	public static long dateString2Timestamp(String format, String dateString) {
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date timestamp = df.parse(dateString);
			return timestamp.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date().getTime();
	}

	/**
	 * long时间转字符串时间
	 * @param format：目的字符串时间格式
	 * @param timestamp：long时间值
	 * @return 字符串时间
	 */
	public static String timestamp2DateString(String format, Long timestamp) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date(timestamp));
	}
	
	public static void main(String[] args) {
		System.out.println(timestamp2DateString("yyyy-MM-dd HH:mm:ss", 1576638840000L));
		System.out.println(dateString2Timestamp("yyyy-MM-dd HH:mm:ss", "2019-12-18 11:14:00"));
		System.out.println(dateString2DateObject("yyyy-MM-dd HH:mm:ss", "2019-12-18 11:14:00"));

		Calendar calendar = Calendar.getInstance();
		calendar.getTime();// 返回Date
	}
}
