package com.sharfah.util.collections;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Algorithms pertaining to binary trees.
 * <p>
 * A binary tree has up to 2 children.
 * <p>
 * A binary search tree is a binary tree in which
 * all left descendants <= n < all right descendants.
 *
 * @author fahd
 */
public class BinaryTree {

  private BinaryTree() {}

  /**
   * Visit the left branch, then the current node, and finally
   * the right branch. On a binary search tree, this will
   * visit the nodes in ascending order.
   *
   * @param action the action to perform on each node visited
   */
  public static <E> void inOrderTraversal(final BinaryTreeNode<E> node,
                                          final Consumer<E> action) {
    if (node != null) {
      inOrderTraversal(node.getLeft(), action);
      action.accept(node.getData());
      inOrderTraversal(node.getRight(), action);
    }
  }

  /**
   * Visit the current node, then left branch, and finally
   * the right branch.
   *
   * @param action the action to perform on each node visited
   */
  public static <E> void preOrderTraversal(final BinaryTreeNode<E> node,
                                           final Consumer<E> action) {
    if (node != null) {
      action.accept(node.getData());
      preOrderTraversal(node.getLeft(), action);
      preOrderTraversal(node.getRight(), action);
    }
  }

  /**
   * Visit the left branch, then the right branch, and finally
   * the current node. The root is always the last node visited.
   *
   * @param action the action to perform on each node visited
   */
  public static <E> void postOrderTraversal(final BinaryTreeNode<E> node,
                                            final Consumer<E> action) {
    if (node != null) {
      postOrderTraversal(node.getLeft(), action);
      postOrderTraversal(node.getRight(), action);
      action.accept(node.getData());
    }
  }

  /**
   * Traverses each level at a time. Need to use a queue.
   * @param <E>
   * @param root
   * @param action
   */
  public static <E> void levelOrderTraversal(final BinaryTreeNode<E> root,
                                             final Consumer<E> action) {
    final java.util.Queue<BinaryTreeNode<E>> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      final BinaryTreeNode<E> node = queue.poll();
      action.accept(node.getData());
      if (node.getLeft() != null) {
        queue.add(node.getLeft());
      }
      if (node.getRight() != null) {
        queue.add(node.getRight());
      }
    }
  }

  /**
   * Creates a binary search tree from a sorted array.
   * Recursively splits the array to build the left and right subtrees.
   * @param <E>
   * @param list must be sorted
   * @return root node
   */
  public static <E> BinaryTreeNode<E> createFromArray(final List<E> list) {
    final int mid = list.size() / 2;
    final E midElement = list.get(mid);
    final BinaryTreeNode<E> root = new BinaryTreeNode<>(midElement);
    if (list.size() == 1) {
      return root;
    }
    final List<E> left = list.subList(0, mid);
    root.setLeft(createFromArray(left));
    if (mid + 1 < list.size()) {
      final List<E> right = list.subList(mid + 1, list.size());
      root.setRight(createFromArray(right));
    }
    return root;
  }

  /**
   * Returns a map of level to nodes.
   * @param <E>
   * @param root
   * @return map
   */
  public static <E> Map<Integer, List<E>> nodesByLevel(final BinaryTreeNode<E> root) {
    final Map<Integer, List<E>> result = new LinkedHashMap<>();
    nodesByLevel0(root, result, 0);
    return result;
  }

  /**
   * Recursively populates the map for each level.
   * @param <E>
   * @param node
   * @param map
   * @param level
   */
  private static <E> void nodesByLevel0(final BinaryTreeNode<E> node,
                                       final Map<Integer, List<E>> map,
                                       final int level) {
    map.computeIfAbsent(level, ArrayList::new).add(node.getData());
    if(node.getLeft() != null) {
      nodesByLevel0(node.getLeft(), map, level + 1);
    }
    if(node.getRight() != null) {
      nodesByLevel0(node.getRight(), map, level + 1);
    }
  }

  /**
   * Returns the height of the tree.
   * A tree with one node has a height of 0.
   * @param <E>
   * @param root
   * @return the height of the tree
   */
  public static <E> int getHeight(final BinaryTreeNode<E> root) {
    return root == null ? -1 :
      1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));
  }

  /**
   * Checks if this is a binary search tree
   * i.e. all nodes to left <= root < right.
   * Traverse the tree in-order and check that every element is bigger than the previous one.
   *
   * @param <E>
   * @param root
   * @return true if it is, false otherwise
   */
  public static <E extends Comparable<E>> boolean isBinarySearchTree(final BinaryTreeNode<E> root) {
    return isBinarySearchTree0(root, null);
  }

  private static <E extends Comparable<E>> boolean isBinarySearchTree0(final BinaryTreeNode<E> node,
                                                                       final BinaryTreeNode<E> prev) {
    if (node != null) {
      if (!isBinarySearchTree0(node.getLeft(), prev)) {
        return false;
      }
      if (prev != null && node.getData().compareTo(prev.getData()) < 0) {
        return false;
      }
      if (!isBinarySearchTree0(node.getRight(), node)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the common ancestor of p and q.
   * If p and q are both on the left of the node, look in the left subtree.
   * If they are both on the right of the node, look right.
   * If they are on different sides of the node, we have found the common ancestor.
   * @param <E>
   * @param root
   * @param p
   * @param q
   * @return common ancestor or null if none
   */
  public static <E> BinaryTreeNode<E> findCommonAncestor(final BinaryTreeNode<E> root,
      final BinaryTreeNode<E> p, final BinaryTreeNode<E> q) {

    if (root == null || p == root || q == root) {
      return null;
    }

    final boolean pOnLeft = isUnder(root.getLeft(), p);
    final boolean qOnLeft = isUnder(root.getLeft(), q);

    if (pOnLeft != qOnLeft) {
      return root;
    }
    return findCommonAncestor(pOnLeft ? root.getLeft() : root.getRight(), p, q);
  }

  /**
   * Is p under the node?
   * @param node
   * @param p
   * @return
   */
  public static <E> boolean isUnder(final BinaryTreeNode<E> node,
                                    final BinaryTreeNode<E> p) {
    if (node == null) {
      return false;
    }
    if (node == p) {
      return true;
    }
    return isUnder(node.getLeft(), p) || isUnder(node.getRight(), p);
  }

  /**
   * Is t2 a subtree of t1?
   * @param <E>
   * @param t1
   * @param t2
   * @return true if t2 is a subtree of t1
   */
  public static <E> boolean isSubTree(final BinaryTreeNode<E> t1, final BinaryTreeNode<E> t2) {
    if (t1 == null) {
      return false;
    }
    if (t1.getData().equals(t2.getData()) && treesEqual(t1, t2)) {
      return true;
    }
    return isSubTree(t1.getLeft(), t2) || isSubTree(t1.getRight(), t2);
  }

  /**
   * Compares two binary trees recursively.
   * @param <E>
   * @param t1
   * @param t2
   * @return true if they match, false otherwise
   */
  public static <E> boolean treesEqual(final BinaryTreeNode<E> t1,
                                       final BinaryTreeNode<E> t2) {

    if (t1 == null && t2 == null) {
      return true;
    }

    if (t1 == null || t2 == null) {
      return false;
    }

    return t1.getData().equals(t2.getData())
        && treesEqual(t1.getLeft(), t2.getLeft())
        && treesEqual(t1.getRight(), t2.getRight());
  }

  /**
   * Returns the kth node's data in level order traversal.
   * @param <E>
   * @param root
   * @param k the index of the kth node. 0 is root
   * @return the data of the kth node or null
   */
  public static <E> E getKthElement(final BinaryTreeNode<E> root, final int k) {
    final java.util.Queue<BinaryTreeNode<E>> queue = new ArrayDeque<>();
    queue.add(root);
    int i = 0;
    BinaryTreeNode<E> node = null;
    while (!queue.isEmpty()) {
      node = queue.poll();
      if (i++ == k) {
        return node.getData();
      }
      if (node.getLeft() != null) {
        queue.add(node.getLeft());
      }
      if (node.getRight() != null) {
        queue.add(node.getRight());
      }
    }
    return null;
  }
}
