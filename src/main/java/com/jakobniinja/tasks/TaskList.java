package com.jakobniinja.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TaskList {

  public Map<String, List<Task>> tasks = new LinkedHashMap<>();

  public void show() {

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

    for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
      for (Task task : project.getValue()) {
        if (task.getId() == id) {
          task.setDone(done);
          return;
        }
      }
    }
  }
}