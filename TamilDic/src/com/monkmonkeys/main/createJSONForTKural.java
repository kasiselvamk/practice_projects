package com.monkmonkeys.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class createJSONForTKural {
	public static String pathname = "C:\\Users\\kasis\\git\\git\\TamilDic\\tiru\\";
	public static Gson gson = new Gson();
	public static OutputStreamWriter file=null;
	public static Integer i =1;
	public static Map<String,TKbulkDAO> elasticBulk = new HashMap();

	public static staticdata metadata = new staticdata();
	
	public static void main(String[] args) throws Exception {
 		 file=	 new OutputStreamWriter(new FileOutputStream("C:\\Users\\kasis\\git\\git\\TamilDic\\thirukkural.json"), "UTF-8");

		//935
 		try {
			for (int i = 803; i <=935; i++) {
				File in = new File(pathname+""+i+".html");
				//System.out.println(i+":started");
				createJSONforUpload(i,in);
				//System.out.println(i+":done");
			}
		}catch (Exception e) {
      e.printStackTrace();
		}
 		finally {
			file.close();

		}
	}
	
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
 		}
         for (Element element : elTabletr) {
        	Elements  td   = element.getElementsByTag("td");
        	for (Element element2 : td) {
				if(element2.hasClass("poem")) {
					generateelkdata(txt.trim(),(id-802),i++,element2.text());
					System.out.println("Done.");

				//	System.out.println(metadata.getMetaData().get(txt.trim())+","+txt+","+(id-802) );
				//	System.out.println(i+++"."+element2.text());
				}
					
			}
        	
		}
	}

/* 
{
  "kuralid"    :1
  "maintitleid"    :1
  "kural" : data
  maintitle:கடவுள் வாழ்த்து
   "title"  :அறத்துப்பால்
   "titlesub" :பாயிரம்
   "img" :
}
*/
	
	private static void generateelkdata(String txt, int j, Integer i2, String data) throws IOException {
		System.out.println(i2+"-Start.");
	String [] titleandsub = 	metadata.getMetaData().get(txt.trim()).split(",");
	TKbulkDAO bulk = new TKbulkDAO();
	bulk.set_id(Long.parseLong(i2.toString()));
	bulk.set_type("kural");
	    TKDAO dao = new TKDAO();
	    dao.setMaintitle(txt.trim());
	    dao.setTitle(titleandsub[0].trim());
	    dao.setTitlesub(titleandsub[1].trim());
	    dao.setMaintitleid(j);
	    dao.setKuralid(i2);
	    dao.setKural(data.trim());
	    dao.setImg(" ");
	    
	    elasticBulk.put("index", bulk);
	   // System.out.println(gson.toJson(elasticBulk));
	    //System.out.println(gson.toJson(dao));
	    file.write(gson.toJson(elasticBulk));
	 	file.write("\n");
		file.write(gson.toJson(dao));
		file.write("\n");
		file.flush();
		
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
