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

public class GetDecData {

	public static void main(String[] args) throws ClientProtocolException, IOException {
 
		//http://www.tamilvu.org/library/l0B00/html/l0B00ind.htm
		String url = "http://www.tamilvu.org/slet/pmdictionary/lexpser_new.jsp?pageno=";

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = null;HttpResponse response=null;HttpEntity e=null; InputStream data =null;
		//4351
		for(Integer i=1;i<=4351;i++) {
			 request = new HttpGet(url+i);
			 response = client.execute(request);
	           FileUtils.copyInputStreamToFile(response.getEntity().getContent(), new File("C:\\Users\\kasis\\git\\git\\TamilDic\\data\\"+i+".html"));
			   System.out.println(i +" Done");
		}
		
	 
           
	}

}
