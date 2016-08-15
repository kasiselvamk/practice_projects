package com.programmingfree.springservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CallUploadMultiple
{
  @RequestMapping({"/call"})
  public ModelAndView test()
  {
    String message = "Welcome to Spring 4.0 !";
    return new ModelAndView("/uploadMultiple", "message", message);
  }
}
