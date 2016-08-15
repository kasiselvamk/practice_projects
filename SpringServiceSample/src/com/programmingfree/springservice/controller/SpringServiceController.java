package com.programmingfree.springservice.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/service/greeting"})
public class SpringServiceController
{
  public static Gson gson;
  
  @RequestMapping(value={"/{name}"}, method={org.springframework.web.bind.annotation.RequestMethod.GET}, headers={"Accept=application/json"})
  public String getGreeting(@PathVariable String name)
  {
    gson = new Gson();
    return gson.toJson(new DAO());
  }
}
