package com.jakobniinja.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

  public Map<String, List<Task>> task = new LinkedHashMap<>();

  public void show() {

  }

  public List<Task> getTask() {
    return task.values().stream().flatMap(Collection::stream).toList();
  }

  public void addProject(String desc) {

    task.put(desc, new ArrayList<>());
  }

  public void addTask(String project, String description) {

    List<Task> taskList = task.get(project);
    if (project == null) {
      return; // Escape due to misuse of method
    }

    taskList.add(new Task(0, description, false));
  }
}