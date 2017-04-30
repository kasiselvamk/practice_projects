package com.boa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OwnDecySorting {
	static List<String> words = new ArrayList<String>();
	static List<String> decy  = new ArrayList<String>();
	public static void main(String[] args) {

		decy.add("a");decy.add("s");decy.add("d");decy.add("f");decy.add("g");
		words.add("sweet");words.add("apple");words.add("grap");words.add("fan");

		Collections.sort(words,new myOwnSorting(decy));
		for (String val : words) {
			System.out.println(val);
		}
	}	
}

class myOwnSorting implements Comparator<String>{
	static List<String> decy = null;
	myOwnSorting(List<String> decy){
		this.decy = decy;
	}

	@Override
	public int compare(String o1, String o2) {
 		return decy.indexOf(""+o1.charAt(0)) - decy.indexOf(""+o2.charAt(0));
	}

}