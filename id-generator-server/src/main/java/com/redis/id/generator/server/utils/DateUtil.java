package com.redis.id.generator.server.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static final String DATE_FMT_YMDHMSSSSS = "yyyyMMddHHmmssSSS";
	public static SimpleDateFormat format = new SimpleDateFormat();
	public static String formatDate(Date date, String pattern){
		format.applyPattern(pattern);
		return format.format(date);
	}
}
