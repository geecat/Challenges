package com.passport.parking.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.passport.parking.exception.GenericExceptionHandler;
import com.passport.parking.request.CalcRequest;
import com.passport.parking.response.CalcResponse;
import com.passport.parking.response.ResponsePayload;
/**
 * Service interface for calulator.
 * @author Ravi
 *
 */
@Component
public interface ParkingService {

	
	public ResponsePayload<CalcResponse>  addResult(CalcRequest addRequest, HttpServletRequest req) throws GenericExceptionHandler;

	public ResponsePayload<CalcResponse> subResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler;

	public ResponsePayload<CalcResponse> mulResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler;

	public ResponsePayload<CalcResponse> divideResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler;

	public ResponsePayload<CalcResponse> getResult(HttpServletRequest req) throws GenericExceptionHandler;
	
}
