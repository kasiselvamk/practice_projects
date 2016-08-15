package com.programmingfree.springservice.controller;

public final class DAO
{
  Integer id = Integer.valueOf(101);
  
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public Integer getAge()
  {
    return this.age;
  }
  
  public void setAge(Integer age)
  {
    this.age = age;
  }
  
  String name = "Kasi Selvam K";
  Integer age = Integer.valueOf(26);
}
