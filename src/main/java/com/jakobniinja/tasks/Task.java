package com.jakobniinja.tasks;

public class Task {

  private long id = 0L;

  private final String description;


  public Task(long id, String description, boolean done) {
    this.id = id;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}
