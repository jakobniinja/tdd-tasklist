package com.jakobniinja.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TaskListTest {

  private static final String EXPECTED0 = "Best Tdd project so far! write first unit test. progress: 0";

  private static final String EXPECTED = "Best Tdd project so far! write first unit test. progress: X";

  private static final String BEST_TDD_PROJECT_SO_FAR = "Best Tdd project so far!";

  private static final String WRITE_FIRST_UNIT_TEST = "write first unit test.";

  private static final String STRING_ID = "0";

  private static final String STRING_ID1 = "1";

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

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

    assertEquals("", outContent.toString());
    assertTrue(taskList.getTasks().isEmpty());
  }

  @Test
  void onShowHasItem() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.setDone(STRING_ID, true);

    taskList.show();

    assertEquals(EXPECTED, outContent.toString());
  }

  @Test
  void onShowHasItemUndone() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.setDone(STRING_ID, false);

    taskList.show();

    assertEquals(EXPECTED0, outContent.toString());
  }

  @Test
  void onAddProjectNotEmpty() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);

    assertFalse(taskList.tasks.keySet().isEmpty());
  }


  @Test
  void onAddTaskNull() {
    taskList.addTask(null, WRITE_FIRST_UNIT_TEST);
    assertTrue(taskList.getTasks().isEmpty());
  }

  @Test
  void onAddTaskValid() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    assertEquals(1, taskList.getTasks().size());
  }

  @Test
  void onMatchDoneTrue() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.setDone(STRING_ID, true);

    assertTrue(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onMissDoneFalse() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.setDone(STRING_ID1, true);

    assertFalse(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onCheckReturnTrue() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.check(STRING_ID1, true);

    assertFalse(taskList.getTasks().stream().anyMatch(Task::isDone));
  }

  @Test
  void onInitUnsetDone() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.setDone(STRING_ID1, false);

    assertTrue(taskList.getTasks().stream().noneMatch(Task::isDone));
  }

  @Test
  void onUncheckFalse() {
    taskList.addProject(BEST_TDD_PROJECT_SO_FAR);
    taskList.addTask(BEST_TDD_PROJECT_SO_FAR, WRITE_FIRST_UNIT_TEST);
    taskList.unCheck(STRING_ID1, false);

    assertTrue(taskList.getTasks().stream().noneMatch(Task::isDone));
  }

  @Test
  void onUnknownCommandError() {
    String command = "foo";
    taskList.error(command);

    assertEquals("Can't recognize the command: " + command, outContent.toString());
  }

  @Test
  void onProjectSubCommand() {

    taskList.add("project my-task");
    assertEquals(1, taskList.tasks.size());
  }

  @Test
  void onAddNotProject() {
    taskList.add("zero my-task");
    assertEquals(0, taskList.tasks.size());
  }

  @Test
  void onTaskSubCommand() {

    taskList.addProject("tdd");
    taskList.add("task tdd workshop");
    assertEquals(1, taskList.tasks.size());
  }

  @ParameterizedTest
  @ValueSource(strings = {"Commands:", "  show", "  add project <project name>",
      "  add task <project name> <task description>", "  check <task id>",
      "  uncheck <task id>", "\n"})
  void onHelpParams(String argument) {
    taskList.help();
    assertTrue(outContent.toString().contains(argument));
  }
}
