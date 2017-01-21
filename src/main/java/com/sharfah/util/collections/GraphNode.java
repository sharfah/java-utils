package com.sharfah.util.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * A node of a graph.
 * @author fahd
 */
public class GraphNode<E> {

  private final E data;
  private final List<GraphNode<E>> children;
  private boolean visited;

  public GraphNode(final E data) {
    this.data = data;
    this.children = new ArrayList<>();
  }

  public E getData() {
    return data;
  }

  public boolean isVisited() {
    return visited;
  }

  public void setVisited(final boolean visited) {
    this.visited = visited;
  }

  public List<GraphNode<E>> getChildren() {
    return children;
  }

  /**
   * Adds a child to this node.
   * @param data the data to add
   * @return the node that was added
   */
  public GraphNode<E> addChild(final E data) {
    final GraphNode<E> toAdd = new GraphNode<>(data);
    return addChild(toAdd);
  }

  /**
   * Add a child node
   * @param data
   * @return the node that was added
   */
  public GraphNode<E> addChild(final GraphNode<E> data) {
    children.add(data);
    return data;
  }
}
