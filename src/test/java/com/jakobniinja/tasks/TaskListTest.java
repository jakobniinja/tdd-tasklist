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
  void onAddTaskNotEmpty() {
    taskList.addProject("New tdd-java project");
    assertFalse(taskList.getTask().isEmpty());
  }
}
