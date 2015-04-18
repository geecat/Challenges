package com.geecat.srm622.div2;

public class FibonacciDiv2 {

	public int find(int N){
		int prev = 0;
		//int prevminus1 = 1;
		int curr = 1;
		while(curr<N){
			int temp = curr;
			curr = curr+prev;
			prev = temp;
		}
		if(N-prev<curr-N){
			return N-prev;
		}else{
			return curr-N;
		}
	}
	
	
	/*public static void main(String[] args) {
		FibonacciDiv2 fib = new FibonacciDiv2();
		System.out.println(fib.find(15));

	}
*/
}
