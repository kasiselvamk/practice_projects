package com.gov.holiday.calendar.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class util {

	
	public static JSONObject createEvent(String name,String description,String start_time, String access_token) throws JSONException, ParseException, IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        JSONObject jsonObj;
        try {
        	JSONObject postjson = new JSONObject();
        	
            HttpPost httpPost = new HttpPost("https://apis.live.net/v5.0/me/events?access_token="+access_token);
            httpPost.addHeader("Content-Type", "application/json");
            postjson.put("name", name);
            postjson.put("description",description);
            postjson.put("start_time", start_time+"T18:30:00.000-0000"); //for n th 12:00 AM
            postjson.put("reminder_time", 5 );
            postjson.put("location", "india");
            postjson.put("is_all_day_event", false);
            postjson.put("availability", "busy");
            postjson.put("visibility", "private");
            StringEntity params =new StringEntity(postjson.toString());
            //System.out.println(EntityUtils.toString(params));
            httpPost.setEntity(params);
            CloseableHttpResponse response1 = httpclient.execute(httpPost);
            try {
            	//System.out.println(response1.getStatusLine());
            	HttpEntity entity1 = response1.getEntity();
            	 
            	//System.out.println(entity1.getContentType());
            	  String jsonstr = EntityUtils.toString(entity1);
            	  jsonObj = new JSONObject(jsonstr);
            	  jsonObj.append("status", response1.getStatusLine());
            	 //System.out.println(jsonObj);
            } finally {
                response1.close();
            }

        } finally {
            httpclient.close();
        }
        
        return jsonObj;
    
	}
	
	public static StringBuilder getEvents(File fXmlFile) throws java.text.ParseException{


		Document   doc =null;
		DateFormat dateFormat =null;
		SimpleDateFormat format1 = null;
		String dtstr,datestrmmm,formatted;
		Integer idatestrdd;
		Date date,datefmt;
		StringBuilder sb = new StringBuilder();
		try {
			dateFormat = new SimpleDateFormat("MMM dd yyyy");
			format1 = new SimpleDateFormat("yyyy-MM-dd");
			DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(fXmlFile);

		}catch(Exception e){
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("div");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getAttributes().getNamedItem("class").getNodeValue().toString().equals("harc_date" )|| nNode.getAttributes().getNamedItem("class").getNodeValue().toString().equals("harc_name" ) ) {
				Element eElement = (Element) nNode;
				//System.out.println( nNode.getAttributes().getNamedItem("class").getNodeValue().toString() +"  : " + eElement.getFirstChild().getTextContent());
				if ( !nNode.getAttributes().getNamedItem("class").getNodeValue().toString().equals("harc_date" ) )sb.append( eElement.getFirstChild().getTextContent() ).append("#");
				if(nNode.getAttributes().getNamedItem("class").getNodeValue().toString().equals("harc_date")){
					dtstr = eElement.getFirstChild().getTextContent().toString().split(",")[0].trim();
					datestrmmm =dtstr.split(" ")[0];
					idatestrdd = Integer.parseInt(dtstr.split(" ")[1]);
					date = dateFormat.parse(datestrmmm+" "+idatestrdd+" 2014");
					datefmt = (new Date(date.getTime() - 1 * 86400000)) ;
					formatted = format1.format(datefmt.getTime());
					//System.out.println(formatted);
					sb.append(formatted).append("~");
				}
			}}
		//System.out.println("sb:"+sb.toString());
	return sb;
	}
	
}
