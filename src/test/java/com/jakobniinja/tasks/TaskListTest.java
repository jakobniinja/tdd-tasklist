package com.jakobniinja.tasks;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class TaskListTest {

  TaskList taskList = new TaskList();

  @Test
  void onInitNotNull(){
    assertNotNull(taskList);
  }

  @Test
  void onInitShowEmpty(){
    fail("todo impl show");
  }
}
