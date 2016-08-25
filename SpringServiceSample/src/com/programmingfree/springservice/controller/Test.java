package com.programmingfree.springservice.controller;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

public class Test {
	public static Integer UPLOAD_MAX_COUNT = 10;
	public static String BLACKLIST_FILEFORMAT =  "([^\\s]+(?i)(doc|png|gif|bmp)$)";
	public static Integer UPLOAD_MAX_SIZE_MB = 10;
	public static Long ONE_MB_TO_BYTES = 1048576L;

	public static Long UPLOAD_MAX_SIZE_BYTES = UPLOAD_MAX_SIZE_MB * ONE_MB_TO_BYTES ;
	/*
	 * maxcount
	 * black list validation's
	 * max size
	 * citi doc input req.
	 * start up loding req config.
	 * 
	 * */
	public static void main(String[] args) {
		System.out.println("isMaxUploadCountBreach:"+isMaxUploadCountBreach(9,1));
		File q = new File ("E:\\apache-tomcat-8.0.28.zip");

		System.out.println("isValidFileFormat:"+isValidFileFormat(q.getName()));

		System.out.println("isValidFileSize:"+isValidFileSize(q));
	}

	public static Boolean isMaxUploadCountBreach(Integer jscount,Integer curcount){
		return jscount+curcount <= UPLOAD_MAX_COUNT ? Boolean.FALSE : Boolean.TRUE;	
	}

	public static Boolean isValidFileFormat(String fileName){ 
		if(!isNullEmpty(fileName)){
			String ext = FilenameUtils.getExtension(fileName);
			return !fileName.matches(BLACKLIST_FILEFORMAT);

		}else
			return Boolean.FALSE ;

	}

	public static Boolean isNullEmpty(String val){
		if(val != null){
			val = val.trim();
			if(val.isEmpty()){
				return Boolean.TRUE;
			} 
		}else {
			return Boolean.TRUE;
		}
		return Boolean.FALSE ;
	}

	public static Boolean isValidFileSize(File f){
		try {
			Long sz = f.length(); // in Bytes
			if(sz == 0L)return Boolean.FALSE;  // For File not exist.
			return (sz <= UPLOAD_MAX_SIZE_BYTES)? Boolean.TRUE : Boolean.FALSE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}
}
