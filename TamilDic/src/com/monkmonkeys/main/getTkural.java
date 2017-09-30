package com.monkmonkeys.main;

import java.io.File;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class getTkural {
	//"l2100se2.jsp?&book_id=31&head_id=1&auth_id=29:31:30:32:33:34:35:"
	//http://www.tamilvu.org/slet/l2100/l2100uri.jsp?song_no=1330&book_id=31&head_id=28&auth_id=29:31:30:32:33:34:35:
	public static void main(String[] args) {
 
		//http://www.tamilvu.org/library/l0B00/html/l0B00ind.htm
		String url = "http://www.tamilvu.org/slet/l2100/l2100are1.jsp?sno=";

		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = null;HttpResponse response=null;HttpEntity e=null; InputStream data =null;
		//4351
		for(Integer i=1;i<=1330;i++) {
			 request = new HttpGet(url+i+"&book_id=31");
			 response = client.execute(request);
	           FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("/home/kasi/eclipse-workspace/TamilDic/data/"+i+".html"));
			   System.out.println(i +" Done");
		}
		
	 
           
	}

}
