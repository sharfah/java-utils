package com.sharfah.util.collections;

/**
 * A node of a binary tree.
 * Has up to two children.
 * @author fahd
 *
 * @param <E>
 */
public class BinaryTreeNode<E> {

  private final E data;
  private BinaryTreeNode<E> left;
  private BinaryTreeNode<E> right;

  /**
   * Creates a binary tree node with no children.
   * @param data
   */
  public BinaryTreeNode(final E data) {
    this.data = data;
  }

  /**
   * @return the data
   */
  public E getData() {
    return data;
  }

  /**
   * @return left node
   */
  public BinaryTreeNode<E> getLeft() {
    return left;
  }

  /**
   * @return right node
   */
  public BinaryTreeNode<E> getRight() {
    return right;
  }
  /**
   * Set the left node.
   * @param left
   */
  public void setLeft(final BinaryTreeNode<E> left) {
    this.left = left;
  }

  /**
   * Set the right node.
   * @param right
   */
  public void setRight(final BinaryTreeNode<E> right) {
    this.right = right;
  }
}
