/**
 * Date time helper 
 */

package web.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateTime is an helper for manipulating Date Time 
 * 
 */
public class DateTime {
	
	/**
	 * This method is intended to format date-time using the specified formatter
	 * 
	 * @param localDateTime the local date time
	 * @param pattern the formatter pattern to use
	 * 
	 */
	public static String format(LocalDateTime localDateTime, String pattern) {
	    return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}
	
}
