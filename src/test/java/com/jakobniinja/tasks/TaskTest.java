package com.jakobniinja.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TaskTest {

  Task task = new Task(223412222L, "Read book for 20 minutes", false);

  @Test
  void onInitTask() {
    assertNotNull(task);
  }

  @Test
  void onTaskGetId() {

    Task task = new Task(0, "Read book for 20 minutes", false);
    assertEquals(0L, task.id());
  }

  @Test
  void getDescAfterConstruction() {
    assertEquals("Read book for 20 minutes", task.description());
  }

  @Test
  void onTaskInitNotDone(){
    assertFalse(task.done());
  }

  @Test
  void onTaskDone(){
    task = new Task(0, "Done task", true);
    assertTrue(task.done());
  }
}
