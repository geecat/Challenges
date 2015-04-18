package com.passport.parking;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.passport.parking.request.CalcRequest;
import com.passport.parking.service.ParkingService;

public class ServiceLayerTest {

	@Autowired ParkingService parkingService;
	@Test
	public void test() {
		CalcRequest addRequest= new CalcRequest();
		addRequest.setNum1(1);
		addRequest.setNum2(2);
		Assert.assertNotNull(addRequest);
		//ResponsePayload<CalcResponse> response = parkingService.addResult(addRequest, HttpServletRequest req);
	}

}
