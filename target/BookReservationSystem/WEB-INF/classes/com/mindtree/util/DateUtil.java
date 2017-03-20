package com.mindtree.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil {
	public static Date getCurrentDate() {
		Calendar calender = Calendar.getInstance();
		Date currentDate = new Date(calender.getTime().getTime());
		return currentDate;
	}

	public static Date getExpirationDate() {
		return new Date(DateUtils.addDays(getCurrentDate(), 7).getTime());
	}

	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			date = getSQLDate(df.parse(dateString));
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	public static Date getSQLDate(java.util.Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		return new Date(calender.getTime().getTime());
	}
}
