package com.github.dice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Player {

   private int id;

   private String userName;

   private String pwd;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPwd() {
      return pwd;
   }

   public void setPwd(String pwd) {
      this.pwd = pwd;
   }

   @Override
   public String toString() {
      return ToStringBuilder.reflectionToString(this);
   }
}
