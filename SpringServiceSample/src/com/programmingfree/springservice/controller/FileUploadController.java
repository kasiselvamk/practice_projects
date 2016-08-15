package com.programmingfree.springservice.controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestController
public class FileUploadController
{
  private static final Logger logger = Logger.getLogger(FileUploadController.class);
  @Resource(name="restProperties")
  private Properties _audit;
  
  @RequestMapping(value={"/uploadMultipleFile"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
  String uploadMultipleFileHandler(@RequestParam("isAutoAuth") Boolean isAutoAuth, @RequestParam("name") String[] names, @RequestParam("file") CommonsMultipartFile[] files)
  {
    ResponseDao resp = null;
    ArrayList<ResponseDao> InfoList = new ArrayList();
    int index = 0;int poss = 0;
    try
    {
      List<CommonsMultipartFile> commonsMultipartFileList = new ArrayList(Arrays.asList(files));
      List<String> nameList = new ArrayList(Arrays.asList(names));
      for (CommonsMultipartFile commonsMultipartFile : commonsMultipartFileList)
      {
        resp = new ResponseDao();
        FileItem item = commonsMultipartFile.getFileItem();
        if ((!item.isFormField()) && (item.getSize() > 0L))
        {
          String fileName = item.getName();
          
          System.out.println("fileName:" + fileName);
          File uploadedFile = new File(this._audit.getProperty("audit.destiny.path") + fileName);
          resp.setMessage("Success!");
          try
          {
            item.write(uploadedFile);
          }
          catch (Exception e)
          {
            resp.setCode(1);
            resp.setMessage(e.getMessage());
          }
          if (isAutoAuth.booleanValue()) {
            UploadToCtiDoc();
          }
          InfoList.add(resp);
        }
        else
        {
          resp.setCode(1);
          resp.setMessage("Not an valid file upload method or multipart/form-data is empty");
          InfoList.add(resp);
        }
      }
    }
    catch (Exception e)
    {
      resp.setCode(1);
      resp.setMessage(e.getMessage());
      InfoList.add(resp);
    }
    return new Gson().toJson(InfoList);
  }
  
  private void UploadToCtiDoc()
  {
    System.out.println("UploadToCtiDoc.......");
  }
  
  @RequestMapping(value={"/DeleteMultipleFile"}, method={org.springframework.web.bind.annotation.RequestMethod.POST}, headers={"Accept=application/json"})
  String uploadMultipleFileHandler(@RequestParam("name") String[] names)
  {
    ResponseDao resp = new ResponseDao();
    ArrayList<ResponseDao> InfoList = new ArrayList();
    try
    {
      List<String> nameList = new ArrayList(Arrays.asList(names));
      for (String name : nameList)
      {
        resp = new ResponseDao();
        try
        {
          if ((name != null) && (!"".equals(name.trim())))
          {
            File file = new File(this._audit.getProperty("audit.destiny.path") + name);
            if ((file.exists()) && (!file.isDirectory()))
            {
              file.delete();
              resp.setMessage("Success");
            }
            else
            {
              resp.setCode(1);
              resp.setMessage("File not exist");
            }
            InfoList.add(resp);
          }
          else
          {
            resp.setCode(1);
            resp.setMessage("File name is null or empty.");
            InfoList.add(resp);
          }
        }
        catch (Exception e)
        {
          resp.setCode(1);
          resp.setMessage(e.getMessage());
          InfoList.add(resp);
        }
      }
    }
    catch (Exception e)
    {
      resp.setCode(1);
      resp.setMessage(e.getMessage());
      InfoList.add(resp);
    }
    return new Gson().toJson(InfoList);
  }
}
