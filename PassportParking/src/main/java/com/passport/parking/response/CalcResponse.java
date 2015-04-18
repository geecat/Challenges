package com.passport.parking.response;

import java.util.List;

public class CalcResponse {
	private int result;
	private List<String> history;
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the history
	 */
	public List<String> getHistory() {
		return history;
	}
	/**
	 * @param history the history to set
	 */
	public void setHistory(List<String> history) {
		this.history = history;
	}
}
