package com.jakobniinja.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskListTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

  private final PrintStream originalOut = System.out;

  TaskList taskList = new TaskList();

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outContent));
  }

  @Test
  void onInitNotNull() {
    assertNotNull(taskList);
  }

  @Test
  void onInitShowEmpty() {
    taskList.show();

    assertTrue(taskList.getTasks().isEmpty());
  }

  @Test
  void onShowHasItem() {
    taskList.addProject("Best Tdd project so far!");
    taskList.addTask("Best Tdd project so far!", "write first unit test.");

    taskList.show();

    assertEquals("You have one task", outContent.toString());
  }

  @Test
  void onAddProjectNotEmpty() {
    taskList.addProject("New tdd-java project");

    assertFalse(taskList.tasks.keySet().isEmpty());
  }


  @Test
  void onAddTaskNull() {
    taskList.addTask(null, "my first testing task");
    assertTrue(taskList.getTasks().isEmpty());
  }

  @Test
  void onAddTaskValid() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    assertEquals(1, taskList.getTasks().size());
  }

  @Test
  void onMatchDoneTrue() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.setDone("0", true);

    assertTrue(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onMissDoneFalse() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.setDone("1", true);

    assertFalse(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onCheckReturnTrue() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.check("1", true);

    assertFalse(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onInitUnsetDone() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.setDone("1", false);

    assertTrue(taskList.getTasks().stream().noneMatch(Task::isDone));
  }


  @Test
  void onUncheckFalse() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.unCheck("1", false);

    assertTrue(taskList.getTasks().stream().noneMatch(Task::isDone));
  }

  @Test
  void onPreviouslyFalse() {
    taskList.addProject("New tdd-java project");
    taskList.addTask("New tdd-java project", "my first testing task");
    taskList.setDone("1", true);
    taskList.setDone("1", false);

    assertTrue(taskList.getTasks().stream().noneMatch(Task::isDone));
  }
}
