package com.geecat.srm144.div1;

import java.util.Arrays;

public class BinaryCode{

	public String[] decode(String msg){
		String[] result= new String[2];
		int[] decodedMsg = new int[msg.length()+2];
		decodedMsg[0] = 0;
		
		result[0] = decode1(decodedMsg,msg,0);
		result[1] = decode1(decodedMsg,msg,1);
		
		return result;
		
	}
	
	private String decode1(int[] tempArr, String msg,int firstBit){
		String result;
		tempArr[1] = firstBit;
		int offset = 1;
		for (int i = 0; i < msg.length(); i++) {
			tempArr[i+1+offset]=((msg.charAt(i)-'0')-tempArr[i+offset]-tempArr[i-1+offset]);
			if(tempArr[i+1+offset]!=0 && tempArr[i+1+offset]!=1){
				return "NONE";
			}
		}
		result = Arrays.toString(tempArr).replace(", ", "");
		return result;// result.substring(2, result.length()-2); // since out put comes in this format I substring by 2 [00111000110]
	}
	
	public static void main(String args[]){
		BinaryCode bc = new BinaryCode();
		String msg = "11";
	/* 
	 * 123210122
	 * Returns: { "011100011",  "NONE" }
	 * "11"  
	 * Returns: { "01",  "10" }
	 * "22111" Returns: { "NONE",  "11001" }
	 * "123210120" Returns: { "NONE",  "NONE" }
	 * 
		"12221112222221112221111111112221111" Returns: 
		{ "01101001101101001101001001001101001",
		"10110010110110010110010010010110010" }
*/		
		
		String[] decode = bc.decode(msg);
		for (int i = 0; i < decode.length; i++) {
			System.out.println(decode[i]);
		}
	}
	
}