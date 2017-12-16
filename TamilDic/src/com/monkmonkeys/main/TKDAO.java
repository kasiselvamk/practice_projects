package com.monkmonkeys.main;

/*
 *   "kuralid"    :1
  "titleid"    :1
  "kural" : data
   "title"  :அறத்துப்பால்
   "titlesub" :பாயிரம்
   "img" :

 * 
 * */

public class TKDAO {
	Integer kuralid;
	String maintitle;
	public String getMaintitle() {
		return maintitle;
	}
	public void setMaintitle(String maintitle) {
		this.maintitle = maintitle;
	}
	public Integer getKuralid() {
		return kuralid;
	}
	public void setKuralid(Integer kuralid) {
		this.kuralid = kuralid;
	}
	
	public String getKural() {
		return kural;
	}
	public void setKural(String kural) {
		this.kural = kural;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitlesub() {
		return titlesub;
	}
	public void setTitlesub(String titlesub) {
		this.titlesub = titlesub;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	Integer maintitleid;
	public Integer getMaintitleid() {
		return maintitleid;
	}
	public void setMaintitleid(Integer maintitleid) {
		this.maintitleid = maintitleid;
	}
	String kural;
	String title;
	String titlesub;
	String img;
	
	
	
}
