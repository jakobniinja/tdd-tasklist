package com.jakobniinja.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskListTest {

  TaskList taskList = new TaskList();

  @Test
  void onInitNotNull() {
    assertNotNull(taskList);
  }

  @Test
  void onInitShowEmpty() {
    taskList.show();

    assertTrue(taskList.getTask().isEmpty());
  }

  @Test
  void onAddProjectNotEmpty() {
    taskList.addProject("New tdd-java project");

    assertFalse(taskList.task.keySet().isEmpty());
  }


  @Test
  void onAddTaskNull() {
    taskList.addTask(null, "my first testing task");
    assertTrue(taskList.getTask().isEmpty());
  }

  @Test
  void onAddTaskValid() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    assertEquals(1, taskList.getTask().size());
  }
}
