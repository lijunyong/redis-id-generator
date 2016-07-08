package com.redis.id.generator.client.utils;


public class StringUtil {
	/**
	 * Checks if a String is non null and is not empty (length > 0).
	 * 
	 * @param aStr
	 *            the string to check
	 * @return true if the String is non-null, and not length zero
	 */
	public static boolean isNotEmpty(String aStr) {

		return (aStr != null && aStr.length() > 0);
	}

	/**
	 * Checks if a (trimmed) String is null or empty.
	 * 
	 * @param aStr
	 *            the string to check
	 * @return true if the String is null, or length zero once trimmed
	 */
	public static boolean isEmpty(String aStr) {

		return (aStr == null || aStr.trim().length() == 0);
	}
}
