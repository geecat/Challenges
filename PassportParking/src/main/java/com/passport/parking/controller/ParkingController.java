package com.passport.parking.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.passport.parking.exception.GenericExceptionHandler;
import com.passport.parking.request.CalcRequest;
import com.passport.parking.response.CalcResponse;
import com.passport.parking.response.ResponsePayload;
import com.passport.parking.service.ParkingService;
import com.passport.parking.util.ResponseUtil;

/**
 * Controller for Calculator related operations.
 * 
 * @author Ravi
 * 
 */
@Controller
public class ParkingController {
	private static final Logger logger = LoggerFactory.getLogger(ParkingController.class);

	@Autowired
	ResponseUtil responseUtil;

	@Autowired
	ParkingService parkingService;

	@RequestMapping(value = "/calculateAdd", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody
	ResponsePayload<CalcResponse> addNumber(@RequestBody CalcRequest reqPayload, HttpServletRequest req, HttpServletResponse res) {
		logger.info("Entering add method.", reqPayload);
		logger.debug(reqPayload.toString());
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		try {
			response = parkingService.addResult(reqPayload, req);
		} catch (GenericExceptionHandler e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(e.getStatusCode());
		}
		responseUtil.setHeader(res, response.getStatusCode());
		logger.info("Exiting add Controller method.");
		return response;
	}

	@RequestMapping(value = "/calculateSub", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody
	ResponsePayload<CalcResponse> subNumber(@RequestBody CalcRequest reqPayload, HttpServletRequest req, HttpServletResponse res) {
		logger.info("Entering subtract method.", reqPayload);
		logger.debug(reqPayload.toString());
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		try {
			response = parkingService.subResult(reqPayload, req);
		} catch (GenericExceptionHandler e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(e.getStatusCode());
		}
		responseUtil.setHeader(res, response.getStatusCode());
		logger.info("Exiting subtract Controller method.");
		return response;
	}

	@RequestMapping(value = "/calculateMul", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody
	ResponsePayload<CalcResponse> mulNumber(@RequestBody CalcRequest reqPayload, HttpServletRequest req, HttpServletResponse res) {
		logger.info("Entering Multiply method.", reqPayload);
		logger.debug(reqPayload.toString());
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		try {
			response = parkingService.mulResult(reqPayload, req);
		} catch (GenericExceptionHandler e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(e.getStatusCode());
		}
		responseUtil.setHeader(res, response.getStatusCode());
		logger.info("Exiting Multiply Controller  method.");
		return response;
	}

	@RequestMapping(value = "/calculateDivide", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody
	ResponsePayload<CalcResponse> divideNumber(@RequestBody CalcRequest reqPayload, HttpServletRequest req, HttpServletResponse res) {
		logger.info("Entering Devide method.", reqPayload);
		logger.debug(reqPayload.toString());
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		try {
			response = parkingService.divideResult(reqPayload, req);
		} catch (GenericExceptionHandler e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(e.getStatusCode());
		}
		responseUtil.setHeader(res, response.getStatusCode());
		logger.info("Exiting Devide Controller method.");
		return response;
	}

	@RequestMapping(value = "/calculateRefresh", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public @ResponseBody
	ResponsePayload<CalcResponse> refreshCalculator(HttpServletRequest req, HttpServletResponse res) {
		logger.info("Entering refresh method.");
		ResponsePayload<CalcResponse> response = new ResponsePayload<CalcResponse>();
		try {
			response = parkingService.getResult(req);
		} catch (GenericExceptionHandler e) {
			response.setMessage(e.getMessage());
			response.setStatusCode(e.getStatusCode());
		}
		responseUtil.setHeader(res, response.getStatusCode());
		logger.info("Exiting Refresh Controller method.");
		return response;
	}
}
