package com.passport.parking.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.passport.parking.constant.Constant;
import com.passport.parking.controller.ParkingController;
import com.passport.parking.exception.GenericExceptionHandler;
import com.passport.parking.request.CalcRequest;
import com.passport.parking.response.CalcResponse;
import com.passport.parking.response.ResponsePayload;
import com.passport.parking.service.ParkingService;
import com.passport.parking.util.ParkingUtil;
import com.passport.parking.util.RequestStorage;
/**
 * Service implementation
 * @author Ravi
 *
 */
@Component
public class ParkingServiceImpl implements ParkingService {
	private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ParkingUtil parkingUtil;

	
	@Override
	public ResponsePayload<CalcResponse> addResult(CalcRequest addRequest, HttpServletRequest req) throws GenericExceptionHandler {
		long start = System.currentTimeMillis();
		logger.info("Entering ParkingServiceImpl.addResult() method");
		logger.debug("Request {}",addRequest.toString());
		String requestForSave;
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		CalcResponse calcResponse = new CalcResponse();
		RequestStorage storage = RequestStorage.getInstance();
		int result = addRequest.getNum1() + addRequest.getNum2();
		logger.debug("Result is {} ",result);
		requestForSave = addRequest.getNum1() + " + " + addRequest.getNum2() + " = " + result + " " + parkingUtil.getRequestDetails(req);
		String id = UUID.randomUUID().toString();
		storage.setRequest(id, requestForSave);
		calcResponse.setResult(result);
		calcResponse.setHistory(storage.getAllRequest());
		response.setData(calcResponse);
		response.setStatusCode(Constant.REST_OK);
		logger.info("Exiting ParkingServiceImpl.addResult() method");
		logger.info("Time Taken at serive layer is {}",System.currentTimeMillis()-start);
		return response;
	}

	@Override
	public ResponsePayload<CalcResponse> subResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler {
		long start = System.currentTimeMillis();
		logger.info("Entering ParkingServiceImpl.subResult() method");
		logger.debug("Request {}",reqPayload.toString());
		String requestForSave;
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		CalcResponse calcResponse = new CalcResponse();
		RequestStorage storage = RequestStorage.getInstance();
		int result = reqPayload.getNum1() - reqPayload.getNum2();
		logger.debug("Result is {} ",result);
		requestForSave = reqPayload.getNum1() + " - " + reqPayload.getNum2() + " = " + result + " " + parkingUtil.getRequestDetails(req);
		String id = UUID.randomUUID().toString();
		storage.setRequest(id, requestForSave);
		calcResponse.setResult(result);
		calcResponse.setHistory(storage.getAllRequest());
		response.setData(calcResponse);
		response.setStatusCode(Constant.REST_OK);
		logger.info("Exiting ParkingServiceImpl.subResult() method");
		logger.info("Time Taken at serive layer is {}",System.currentTimeMillis()-start);
		return response;
	}

	@Override
	public ResponsePayload<CalcResponse> mulResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler {
		long start = System.currentTimeMillis();
		logger.info("Entering ParkingServiceImpl.mulResult() method");
		logger.debug("Request {}",reqPayload.toString());
		String requestForSave;
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		CalcResponse calcResponse = new CalcResponse();
		RequestStorage storage = RequestStorage.getInstance();
		int result = reqPayload.getNum1() * reqPayload.getNum2();
		logger.debug("Result is {} ",result);
		requestForSave = reqPayload.getNum1() + " * " + reqPayload.getNum2() + " = " + result + " " + parkingUtil.getRequestDetails(req);
		String id = UUID.randomUUID().toString();
		storage.setRequest(id, requestForSave);
		calcResponse.setResult(result);
		calcResponse.setHistory(storage.getAllRequest());
		response.setData(calcResponse);
		response.setStatusCode(Constant.REST_OK);
		logger.info("Exiting ParkingServiceImpl.mulResult() method");
		logger.info("Time Taken at serive layer is {}",System.currentTimeMillis()-start);
		return response;
	}

	@Override
	public ResponsePayload<CalcResponse> divideResult(CalcRequest reqPayload, HttpServletRequest req) throws GenericExceptionHandler {
		long start = System.currentTimeMillis();
		logger.info("Entering ParkingServiceImpl.divideResult() method");
		logger.debug("Request {}",reqPayload.toString());
		String requestForSave;
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		CalcResponse calcResponse = new CalcResponse();
		RequestStorage storage = RequestStorage.getInstance();
		if(reqPayload.getNum2()==0){
			logger.debug("Attempt to division by zero.");
			throw new GenericExceptionHandler(Constant.DIVIDE_BY_ZERO,Constant.REST_FAILED_CODE);
		}
		int result = reqPayload.getNum1() / reqPayload.getNum2();
		logger.debug("Result is {} ",result);
		requestForSave = reqPayload.getNum1() + " / " + reqPayload.getNum2() + " = " + result + " " + parkingUtil.getRequestDetails(req);
		String id = UUID.randomUUID().toString();
		storage.setRequest(id, requestForSave);
		calcResponse.setResult(result);
		calcResponse.setHistory(storage.getAllRequest());
		response.setData(calcResponse);
		response.setStatusCode(Constant.REST_OK);
		logger.info("Exiting ParkingServiceImpl.divideResult() method");
		logger.info("Time Taken at serive layer is {}",System.currentTimeMillis()-start);
		return response;
	}

	@Override
	public ResponsePayload<CalcResponse> getResult(HttpServletRequest req) throws GenericExceptionHandler {
		long start = System.currentTimeMillis();
		logger.info("Entering ParkingServiceImpl.getResult() method");
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		CalcResponse calcResponse = new CalcResponse();
		RequestStorage storage = RequestStorage.getInstance();
		calcResponse.setHistory(storage.getAllRequest());
		response.setData(calcResponse);
		response.setStatusCode(Constant.REST_OK);
		logger.info("Exiting ParkingServiceImpl.getResult() method");
		logger.info("Time Taken at serive layer is {}",System.currentTimeMillis()-start);
		return response;
	}

}
