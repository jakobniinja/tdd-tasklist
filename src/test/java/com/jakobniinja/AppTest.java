package com.jakobniinja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class AppTest {


  private final String[] args = new String[]{"Hello World"};

  @Test
  void onInitNotNull() {

    App app = new App();
    assertNotNull(app);
  }

  @Test
  void onMainPrint() {

    App.main(args);
    assertEquals(1, Arrays.stream(args).toList().size());
  }
}
