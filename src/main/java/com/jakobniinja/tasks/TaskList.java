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

    ArrayList<Task> arrayList = new ArrayList<>(List.of(new Task(0, null, false)));
    task.put(desc, arrayList);
  }
}