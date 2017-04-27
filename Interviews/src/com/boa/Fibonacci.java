package com.boa;

public class Fibonacci {

	public static void main(String[] args) {
		 System.out.println(n1 +"  "+ n2 );

		 getFibonacci(10);
		System.out.println("Done..");

	}
	//0,1,1,2,3,5,....
    static int n1=0;
	static int n2=1,tmp=0;
	 static int getFibonacci(int count) {
		 if(count>0){
			 tmp = n2 +n1;
			 n1 = n2;
			 n2 = tmp;
			 System.out.println(tmp);
			 return getFibonacci(--count);
		 }
		return count;
		 
		 
		 
	 }

}
