package com.jakobniinja.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TaskList {

  public Map<String, List<Task>> tasks = new LinkedHashMap<>();

  public void show() {
    for (Entry<String, List<Task>> project : tasks.entrySet()) {
      System.out.print(project.getKey() + " ");
      for (Task task : project.getValue()) {

        final String done = task.isDone() ? "X" : "0";

        System.out.print(task.getDescription() + " progress: " + done);
      }
    }
  }

  public List<Task> getTasks() {
    return tasks.values().stream().flatMap(Collection::stream).toList();
  }

  public void addProject(String desc) {

    tasks.put(desc, new ArrayList<>());
  }

  public void addTask(String project, String description) {

    List<Task> taskList = tasks.get(project);
    if (project == null) {
      return; // Escape due to misuse of method
    }

    taskList.add(new Task(0, description, false));
  }

  public void setDone(String stringId, boolean done) {
    int id = Integer.parseInt(stringId);

    for (Entry<String, List<Task>> project : tasks.entrySet()) {
      for (Task task : project.getValue()) {
        if (task.getId() == id) {
          task.setDone(done);
          return;
        }
      }
    }
  }

  public void check(String stringId, boolean done) {
    setDone(stringId, done);
  }

  public void unCheck(String stringId, boolean done) {
    setDone(stringId, done);
  }

  public void error(String command) {
    System.out.print("Can't recognize the command: " + command);
  }

  public void add(String command) {

    if (command.equals("project")) {
      addProject(command);
    } else if (command.equals("task")) {
      addTask("project", command);
    }
  }
}