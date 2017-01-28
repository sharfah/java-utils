package com.sharfah.util.algorithms;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ParenthesesTest {

  @Test
  public void test() {
    assertThat(Parentheses.generate(1), hasItems("()"));
    assertThat(Parentheses.generate(2), hasItems("()()", "(())"));
    assertThat(Parentheses.generate(3), hasItems("()()()", "((()))", "()(())", "(())()", "(()())"));
  }
}
