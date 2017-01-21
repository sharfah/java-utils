package com.sharfah.util.collections;

import static java.util.stream.Collectors.*;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * Algorithms pertaining to Graphs.
 * <p>
 * Difference between a tree and a graph is that a graph can have cycles.
 * A graph is simply a collection of nodes with edges between (some of) them.
 *
 * @author fahd
 */
public class Graph {

  private Graph() {}

  /**
   * Depth first search. (recursive)
   * We explore each branch completely before moving
   * on to the next branch.
   * Unlike a tree, we must mark nodes so that we don't get into
   * an infinite loop.
   *
   * @param root
   * @param action
   */
  public static <E> void depthFirstTraversal(final GraphNode<E> root, final Consumer<E> action) {
    if (root == null) {
      return;
    }
    action.accept(root.getData());
    root.setVisited(true);
    root.getChildren().stream()
        .filter(n -> !n.isVisited())
        .forEach(n -> depthFirstTraversal(n, action));
  }

  /**
   * Depth first search. (iterative)
   * Uses a stack.
   * @param <E>
   * @param root
   * @param action
   */
  public static <E> void depthFirstTraversalIterative(final GraphNode<E> root, final Consumer<E> action) {
    final java.util.Stack<GraphNode<E>> stack = new java.util.Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      final GraphNode<E> node = stack.pop();
      if (!node.isVisited()) {
        action.accept(node.getData());
        node.setVisited(true);
        node.getChildren().stream()
          .filter(n -> !n.isVisited())
          .collect(collectingAndThen(toList(), l -> { // must reverse the children
            Collections.reverse(l);
            return l;
            }))
          .forEach(stack::push);
      }
    }
  }

  /**
   * Breadth first search.
   * We explore each level before going to the next one.
   * BFS is generally better if we want to find the shortest path (or just any path)
   * between two nodes.
   * @param <E>
   * @param root
   * @param action
   */
  public static <E> void breadthFirstTraversal(final GraphNode<E> root, final Consumer<E> action) {
    final java.util.Queue<GraphNode<E>> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      final GraphNode<E> node = queue.poll();
      if (!node.isVisited()) {
        action.accept(node.getData());
        node.setVisited(true);
        node.getChildren().stream()
          .filter(n -> !n.isVisited())
          .forEach(queue::add);
      }
    }
  }

  /**
   * Checks if a path exists between two nodes.
   * Performs BFS from a until b is reached.
   * @param <E>
   * @param a start node
   * @param b end node
   * @return true if a path exists, false otherwise
   */
  public static <E> boolean pathExists(final GraphNode<E> a, final GraphNode<E> b) {
    if (a == b) {
      return true;
    }
    final java.util.Queue<GraphNode<E>> queue = new ArrayDeque<>();
    queue.add(a);
    while (!queue.isEmpty()) {
      final GraphNode<E> node = queue.poll();
      if (!node.isVisited()) {
        if (node.equals(b)) {
          return true;
        }
        node.setVisited(true);
        node.getChildren().stream()
          .filter(n -> !n.isVisited())
          .forEach(queue::add);
      }
    }
    return false;
  }
}
