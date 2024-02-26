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
    String[] subCommandRest = command.split(" ", 2);
    String subCommand = subCommandRest[0];

    if (subCommand.equals("project")) {
      addProject(subCommandRest[1]);
    } else if (subCommand.equals("task")) {
      String[] projectTask = subCommandRest[1].split(" ", 2);
      addTask(projectTask[0], projectTask[1]);
    }
  }

  public void help() {
    System.out.print("Commands:");
    System.out.print("  show");
    System.out.print("  add project <project name>");
    System.out.print("  add task <project name> <task description>");
    System.out.print("  check <task id>");
    System.out.print("  uncheck <task id>");
    System.out.println();
  }
}