package com.passport.parking.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is used for storage of requests.
 * Requests are not stored to file. They are lost once the app is restarted.
 * @author Ravi
 *
 */
public class RequestStorage {

	private static RequestStorage storage=null;
	static Object object = new Object();
	private RequestStorage(){
		
	}
	public static RequestStorage getInstance(){
		if(storage==null){
			synchronized (object) {
				storage = new RequestStorage();
			}
		}
		return storage;
	}
	
	private static Map<String,String> storageMap = Collections.synchronizedMap(new LinkedHashMap<String,String>());
	public void setRequest(String key, String value) {
		storageMap.put(key, value);
	}
	
	@SuppressWarnings("rawtypes")
	public List<String> getAllRequest() {
		List<String> allRequest = new ArrayList<String>();
		synchronized(storageMap){
			Iterator it = storageMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        allRequest.add((String) pairs.getValue());
		        
		    }
		}
		return allRequest;
	}
	
}
