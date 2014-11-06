package com.gov.holiday.calendar.add;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import com.gov.holiday.calendar.util.util;

public class main {

/*
 * 
 * To get access_token - https://login.live.com/oauth20_authorize.srf?client_id=&scope=wl.events_create,wl.calendars&response_type=token&oauth_callback=oob&redirect_uri=https://login.live.com/oauth20_desktop.srf
 * & past the generated access_token in the below java parameter 'access_token'
 * 
 */
	
  static String access_token = "";
	public static void main(String[] args) {
		File fXmlFile = null;
		StringBuilder sb = null;
		String dt,desc;
		JSONObject respjson =null;
		ArrayList<String> arraylists = null;
		try {
			System.out.println("Starting...");
			fXmlFile= new File("D:/xml.xml");
			sb = util.getEvents(fXmlFile);
			arraylists =  new ArrayList<String> ( Arrays.asList(sb.toString().split("#")));
            for (String str : arraylists) {
            	  dt = str.split("~")[0];
            	  desc  = str.split("~")[1];
            	  respjson = util.createEvent("TN-Gov-Holiday",desc,dt,access_token);
            	  System.out.println(respjson);
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}

}
