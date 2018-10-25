package com.github.dice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Player {

   private int playerId;

   private String userName;

   private String pwd;

   public int getPlayerId() {
      return playerId;
   }

   public void setPlayerId(int playerId) {
      this.playerId = playerId;
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
