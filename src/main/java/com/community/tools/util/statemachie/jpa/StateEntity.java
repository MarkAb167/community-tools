package com.community.tools.util.statemachie.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "state_entity")
public class StateEntity {
  @Id
  private String userID;
  private String gitName;
  private byte[] stateMachine;
  private Integer pointByTask = 0;
  private Integer karma = 0;

  public StateEntity() {
  }

  public StateEntity(String userID, byte[] stateMachine) {
    this.stateMachine = stateMachine;
    this.userID = userID;
  }

  public StateEntity(String userID, String gitName) {
    this.gitName = gitName;
    this.userID = userID;
  }

  public Integer getTotalPoints() {
    return this.karma + this.pointByTask;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public void setGitName(String gitName) {
    this.gitName = gitName;
  }

  public void setStateMachine(byte[] stateMachine) {
    this.stateMachine = stateMachine;
  }

  public String getUserID() {
    return userID;
  }

  public byte[] getStateMachine() {
    return stateMachine;
  }

  public String getGitName() {
    return gitName;
  }

  public void setPointByTask(Integer pointByTask) {
    this.pointByTask = pointByTask;
  }

  public void setKarma(Integer karma) {
    this.karma = karma;
  }



  public Integer getPointByTask() {
    return pointByTask;
  }

  public Integer getKarma() {
    return karma;
  }
}
