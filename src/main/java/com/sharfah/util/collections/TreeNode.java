package com.sharfah.util.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents the node of a tree.
 * @author fahd
 * @param <E>
 */
public class TreeNode<E> {

  private final E data;
  private final List<TreeNode<E>> children;

  /**
   * Creates the tree node with the specified data and no children.
   * @param data
   */
  public TreeNode(final E data) {
    this.data = data;
    this.children = new ArrayList<>();
  }

  /**
   * @return the data contained in this node
   */
  public E getData() {
    return data;
  }

  /**
   * Adds a child to this tree node.
   * @param data the data to add
   */
  public TreeNode<E> addChild(final E data) {
    final TreeNode<E> toAdd = new TreeNode<>(data);
    children.add(toAdd);
    return toAdd;
  }

  /**
   * @return a stream of nodes in this tree in depth first order
   */
  public Stream<TreeNode<E>> stream() {
    return Stream.concat(Stream.of(this), children.stream().flatMap(TreeNode::stream));
  }
}
