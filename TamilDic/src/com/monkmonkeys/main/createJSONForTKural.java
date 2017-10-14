package com.monkmonkeys.main;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class createJSONForTKural {
	public static String pathname = "/home/kasi/eclipse-workspace/TamilDic/data/";
	public static Gson gson = new Gson();
	public static FileWriter file=null;
	public static Integer i =1;
   
	public static staticdata metadata = new staticdata();
	
	public static void main(String[] args) throws Exception {
		file = new FileWriter("/home/kasi/Downloads/thirukkural.json");
		//935
 		try {
			for (int i = 803; i <=935; i++) {
				File in = new File(pathname+"Tkural_"+i+".html");
				//System.out.println(i+":started");
				createJSONforUpload(i,in);
				//System.out.println(i+":done");
			}
		} finally {
			file.close();

		}
	}
/*{
  "kuralid"    :
  "titleid"    :
  "kural" : 
   "pal"  :
   "palsub" :
   "title" :
}*/
	
	static void createJSONforUpload(Integer id , File in) throws Exception {		
		//String metaString = getMetaString(id);
		Document doc = Jsoup.parse(in,"UTF-8");
		Elements el = doc.getElementsByTag("table");
		Element elTable = el.get(0);
		Elements  elTabletr =  elTable.getElementsByTag("tr");
		String txt= "";
		Elements  elTabletd =	elTabletr.get(1).getElementsByTag("td");
		for (Element element : elTabletd) {
			 txt= element.text().trim(); 
		//	System.out.println("metaData.put(\""+txt+"\",\""+metaString+"\");");
		}
         for (Element element : elTabletr) {
        	Elements  td   = element.getElementsByTag("td");
        	for (Element element2 : td) {
				if(element2.hasClass("poem")) {
					System.out.println(metadata.getMetaData().get(txt)+","+txt+","+(id-802) );
					System.out.println(i+++"."+element2.text());
				}
					
			}
        	
		}
	}

	private static String getMetaString(Integer id) {
		String metaString = null;
		if(id>=803 && id<=806 ) {
			metaString ="அறத்துப்பால்,பாயிரம்";
		}else if(id>=807 && id<=826 ) {
			metaString ="அறத்துப்பால்,இல்லற இயல்";
		}else if(id>=827 && id<=839 ) {
			metaString ="அறத்துப்பால்,துறவற இயல்";
		}else if(id==840) {
			metaString ="அறத்துப்பால்,ஊழியல்";
		}else if(id>=841 && id<=865 ) {
			metaString ="பொருட்பால்,அரசு இயல்";
		}else if(id>=866 && id<=897 ) {
			metaString ="பொருட்பால்,அங்கவியல்";
		}else if(id>=898 && id<=910 ) {
			metaString ="பொருட்பால்,ஒழிபு இயல்";
		}else if(id>=911 && id<=917 ) {
			metaString ="காமத்துப்பால்,களவு இயல்";
		}else if(id>=918 && id<=935 ) {
			metaString ="காமத்துப்பால்,கற்பு இயல்";
		}
		return	metaString;
	}
}
