package com.monkmonkeys.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;


//http://www.tamilvu.org/ta/library-libcontnt-273141
//http://www.tamilvu.org/library/l2100/html/l2100ind.htm

public class getTkural {
	//"l2100se2.jsp?&book_id=31&head_id=1&auth_id=29:31:30:32:33:34:35:"
	//http://www.tamilvu.org/slet/l2100/l2100uri.jsp?song_no=1330&book_id=31&head_id=28&auth_id=29:31:30:32:33:34:35:
	
	static String url = "http://www.tamilvu.org/slet/l2100/l2100son.jsp?subid=";
	static Integer code = null ;
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
 
		//http://www.tamilvu.org/library/l0B00/html/l0B00ind.htm
		
		HttpClient client = HttpClientBuilder.create().build();
		
		HttpGet request = null;HttpResponse response=null;HttpEntity e=null; InputStream data =null;
		//935
		for(Integer i=803;i<=935;i++) {
			 request = new HttpGet(url+i);
			 response = client.execute(request);
			 code =  response.getStatusLine().getStatusCode();
              if(code == 200 ) {
            	 FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("C:\\Users\\kasis\\git\\git\\TamilDic\\tiru\\"+i+".html"));
  			   System.out.println(i +" Done");
             }else {
            	 System.out.println(i +"-"+code+" Not Done!");
             }
			 
		}
		
	 
           
	}

}
