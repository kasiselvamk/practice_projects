package com.boa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class emp{
	int ro;
	public int getRo() {
		return ro;
	}

	public String getName() {
		return name;
	}

	String name;

	emp(int ro,String name){
		this.ro = ro;
		this.name = name;
	}
	@Override
	public String toString() {
        return("id:"+ this.ro +"   name:"+this.name);		 
	}
}

class sortbyid implements Comparator<emp>
{
	@Override
	public int compare(emp o1, emp o2) {
		return o1.getRo() - o2.getRo();
	}
	

}

public class CustSorting {
	public static void main(String[] args) {
		ArrayList<emp> eList = new ArrayList<emp>()	;
	  eList.add(new emp(41,"2323"));
	  eList.add(new emp(13,"2323"));

	  eList.add(new emp(32,"2323"));

	  eList.add(new emp(1,"2323"));

	  eList.add(new emp(45,"2323"));

	  eList.add(new emp(10,"2323"));

	  eList.sort(new sortbyid());
	  for (emp emp : eList) {
		System.out.println(emp);
	}
	}
}
