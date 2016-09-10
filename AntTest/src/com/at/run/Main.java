package com.at.run;

public class Main {

	public static void main(String...args){
		try {while (true) {
			System.out.println("I am sleaping :1000");

		    Thread.sleep(1000);

		}} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
