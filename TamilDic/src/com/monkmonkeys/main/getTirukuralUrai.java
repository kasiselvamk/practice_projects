package com.monkmonkeys.main;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;

public class getTirukuralUrai {
	public static FileWriter file=null;

     public static String uri = "http://www.tamilvu.org/slet/l2100/l2100uri.jsp?book_id=31&head_id=26&song_no=";
	
	
	public static void main(String[] args) throws Exception {
		HttpGet request = null;HttpResponse response=null;HttpEntity e=null; InputStream data =null;

		CookieStore cookieStore = new BasicCookieStore(); 
		BasicClientCookie cookie = new BasicClientCookie("auth_id", "29:31:30:32:33:34:35:");
		
		BasicClientCookie cookie1 = new BasicClientCookie("head_id", "26");
		BasicClientCookie cookie2 = new BasicClientCookie("treemenu1", "0,1");

		

		cookie.setDomain(".tamilvu.org");
		cookie.setPath("/");
		
		cookie1.setDomain(".tamilvu.org");
		cookie1.setPath("/");
		
		cookie2.setDomain(".tamilvu.org");
		cookie2.setPath("/");

		cookieStore.addCookie(cookie); 
		cookieStore.addCookie( cookie1); 
		cookieStore.addCookie( cookie2); 
		
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
 		
		for(int i=1;i<=500;i++) {
			 request = new HttpGet(uri+i);
			 response = client.execute (new HttpGet("http://www.tamilvu.org/slet/l2100/l2100sec.jsp?&book_id=31&head_id=26&auth_id=29:31:30:32:33:34:35:"));
			 if(response.getStatusLine().getStatusCode()==200) {
				 
	  			 response = client.execute(request);
		           FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("/home/kasi/eclipse-workspace/TamilDic/t-urai/"+i+".html"));
				   System.out.println(i +" Done");
				 
			 }
		}
		
	 
           
	}

	
}
