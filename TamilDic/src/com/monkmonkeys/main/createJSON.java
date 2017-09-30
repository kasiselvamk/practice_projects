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

public class createJSON {
	public static String pathname = "/home/kasi/Downloads/data/";
	public static DAO dao=null;	
	public static bulkDAO bulk=null;
	public static Long id =-1l;
	public static Integer tmp;
	public static Gson gson = new Gson();
	public static Map<String,bulkDAO> elasticBulk = new HashMap();
	public static FileWriter file=null;

	public static void main(String[] args) throws Exception {
		file = new FileWriter("/home/kasi/Downloads/tamildec.json");
		try {
			for (int i = 1; i <=4351; i++) {
				File in = new File(pathname+i+".html");
				System.out.println(i+":started");
				createJSONforUpload(in);
				System.out.println(i+":done");
			}
		} finally {
			file.close();

		}


	}

	static void createJSONforUpload(File in) throws Exception {
		Document doc = Jsoup.parse(in,"UTF-8");
		Elements el = doc.getElementsByTag("table");
		Element elTable = el.get(4);
		Elements  elTabletr =  elTable.getElementsByTag("tr");
		for (Element element : elTabletr) {
			Elements  elTabletd =  element.getElementsByTag("td");
			dao = new DAO();bulk=null;tmp=0;id =id+1;
			for (Element element2 : elTabletd) {
				if(tmp++ == 0)
					dao.setKey(element2.text());
				else if(tmp++ == 2) 
					dao.setVal(element2.text());
				dao.setId(id);bulk= new bulkDAO();bulk.set_id(id);
			}
			if(bulk!=null) {
				elasticBulk.put("index", bulk);
				file.write(gson.toJson(elasticBulk));
				file.write("\n");
				file.write(gson.toJson(dao));
				file.write("\n");
				file.flush();
			}

		}
	}
}
