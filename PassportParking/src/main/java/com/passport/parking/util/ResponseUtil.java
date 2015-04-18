package com.passport.parking.util;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * Utility class for Servlet header.
 * @author Ravi
 *
 */
@Component
public class ResponseUtil {
	public void setHeader(HttpServletResponse res, String statusCode) {
		int code;
		try {
			code = Integer.parseInt(statusCode);
		} catch (NumberFormatException e) {
			code=500;
		}
		
		res.setStatus(code);
	}
}
