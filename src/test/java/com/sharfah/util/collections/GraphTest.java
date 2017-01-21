package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
  GraphNode<Integer> root;
  GraphNode<Integer> two;
  GraphNode<Integer> five;

  @Before
  public void setUp() {
    root = new GraphNode<>(0);
    final GraphNode<Integer> one = root.addChild(1);
    final GraphNode<Integer> four = root.addChild(4);
    five = root.addChild(5);
    final GraphNode<Integer> three = one.addChild(3);
    one.addChild(four);
    two = three.addChild(2);
    three.addChild(four);
    two.addChild(one);
  }

  @Test
  public void testDepthFirstSearch() {
    final List<Integer> result = new ArrayList<>();
    Graph.depthFirstTraversal(root, result::add);
    assertThat(result, is(Arrays.asList(0, 1, 3, 2, 4, 5)));
  }
  @Test
  public void testDepthFirstSearchIterative() {
    final List<Integer> result = new ArrayList<>();
    Graph.depthFirstTraversalIterative(root, result::add);
    assertThat(result, is(Arrays.asList(0, 1, 3, 2, 4, 5)));
  }

  @Test
  public void testBreadthFirstSearch() {
    final List<Integer> result = new ArrayList<>();
    Graph.breadthFirstTraversal(root, result::add);
    assertThat(result, is(Arrays.asList(0, 1, 4, 5, 3, 2)));
  }

  @Test
  public void testPathExists() {
    assertThat(Graph.pathExists(root, two), is(true));
    assertThat(Graph.pathExists(two, five), is(false));
  }
}
