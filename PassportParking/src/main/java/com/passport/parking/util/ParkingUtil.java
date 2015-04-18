package com.passport.parking.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * 
 * Utility for getting time and request ip address.
 * @author Ravi
 *
 */
@Component
public class ParkingUtil {

	public String getRequestDetails(HttpServletRequest req) {
		String finalRequest = null;
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}
		DateFormat dateFormat = new SimpleDateFormat("h:mm a");
		Calendar cal = Calendar.getInstance();
		String time = dateFormat.format(cal.getTime());

		finalRequest = "( IP " + ipAddress + ", " + time + " )";
		return finalRequest;
	}

}
