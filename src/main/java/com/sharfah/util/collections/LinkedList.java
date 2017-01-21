package com.sharfah.util.collections;

import java.util.Comparator;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A linked list.
 * Each node points to the next node in the list.
 * You can add items to the beginning of the list in constant time.
 * However, unlike an array, you cannot access a
 * particular index in constant time.
 *
 * @author fahd
 * @param <E>
 */
public class LinkedList<E> {

  /**
   * A node in the list
   * @param <E>
   */
  static final class Node<E> {
    E data;
    Node<E> next;

    /**
     * Creates a node with the specified data.
     * @param data
     */
    public Node(final E data) {
      this.data = data;
    }
  }

  private Node<E> head;

  public LinkedList() {
  }

  LinkedList(final Node<E> head) {
    this.head = head;
  }

  /**
   * Returns the last element in the list.
   * Takes O(N) time.
   * @return the tail of the list
   */
  private Node<E> getTail() {
    Node<E> tail = head;
    while (tail.next != null) {
      tail = tail.next;
    }
    return tail;
  }

  /**
   * Adds an item to the head of the list in O(1) time.
   * @param data the data to add
   */
  public void add(final E data) {
    final Node<E> node = new Node<>(data);
    node.next = head;
    head = node;
  }

  /**
   * Append an item to the end of list.
   * Takes O(N) time.
   * @param data the data to add
   */
  public void appendToTail(final E data) {
    final Node<E> node = new Node<>(data);
    if (isEmpty()) {
      head = node;
    }
    else {
      getTail().next = new Node<>(data);
    }
  }

  /**
   * Returns the element at the specified index.
   * @param index
   * @return the element
   * @throws NoSuchElementException if the index is out of bounds
   */
  public E get(final int index) {
    Node<E> n = head;
    for (int i = 0; i < index; i++) {
      if (n == null) {
          throw new NoSuchElementException("No element at index: " + index);
      }
      n = n.next;
    }
    return n.data;
  }

  /**
   * Tests if the list is empty.
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return head == null;
  }

  /**
   * Counts the number of elements in this list.
   * @return the size of the list
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    }
    int count = 0;
    Node<E> n = head;
    while (n != null) {
      n = n.next;
      count++;
    }
    return count;
  }

  /**
   * Deletes the specified element from the list.
   *
   * @param e the item to delete
   * @return true if the item was deleted, false otherwise
   */
  public boolean delete(final E e) {
    if (isEmpty()) {
      return false;
    }

    if (head.data.equals(e)) {
      head = head.next;
      return true;
    }

    // iterate over the list and find the previous node
    // set the previous node's next to next.next.
    Node<E> n = head;
    while (n.next != null) {
      if (n.next.data.equals(e)) {
        n.next = n.next.next;
        return true;
      }
      n = n.next;
    }
    return false;
  }

  /**
   * Tests if this list contains the specified element.
   * Takes O(N).
   * @param e the element to look for
   * @return true if the element exists, false otherwise
   */
  public boolean contains(final E e) {
    if (isEmpty()) {
      return false;
    }

    Node<E> n = head;
    while (n != null) {
      if (n.data.equals(e)) {
        return true;
      }
      n = n.next;
    }
    return false;
  }

  /**
   * Removes duplicates from this list. Uses a temporary set.
   * This solution takes O(N) time.
   */
  public void removeDuplicates() {
    if (isEmpty()) {
      return;
    }

    final Set<E> set = new HashSet<>();
    Node<E> n = head;
    Node<E> prev = n;
    while (n != null) {
      if (set.contains(n.data)) {
        prev.next = n.next;
      }
      else {
        set.add(n.data);
        prev = n;
      }
      n = n.next;
    }
  }

  /**
   * Removes duplicates without using a temporary set.
   * This takes O(1) space but O(N^2) time.
   */
  public void removeDuplicatesInPlace() {
    if (isEmpty()) {
      return;
    }
    Node<E> n = head;
    while (n != null) {

      // delete all future nodes with this value
      Node<E> r = n;
      while (r.next != null) {
        if (n.data.equals(r.next.data)) {
          r.next = r.next.next;
        }
        else {
          r = r.next;
        }
      }

      n = n.next;
    }
  }

  /**
   * Returns the kth to last element.
   * For example, if k=2, the second last element is returned.
   * If k=1, the last element is returned.
   * <p>
   * This algorithm uses two runners p1 and p2, placed k nodes
   * apart. We move them one step at a time until p2 hits
   * the end of the list. At that point, p1 will be k nodes
   * from the end.
   *
   * @param k the number of elements from the end of the list
   * @return the kth to last element
   */
  public E findKthToLastElement(final int k) {
    Node<E> p1 = head;
    Node<E> p2 = head;

    // move p2 k steps ahead
    for (int i = 0; i < k; i++) {
      if (p2 == null) {
        return null;
      }
      p2 = p2.next;
    }

    // move both. When p2 gets to the end, p1 is k steps behind
    while (p2 != null) {
      p2 = p2.next;
      p1 = p1.next;
    }

    return p1.data;
  }

  /**
   * Deletes the specified node. It cannot be the last item in the list.
   * You are only given this node.
   * @param node the node to delete
   */
  public static <E> void delete(final Node<E> node) {
    final Node<E> next = node.next;
    if (next == null) {
      throw new RuntimeException("The node to delete must have a next node");
    }
    node.data = next.data;
    node.next = next.next;
  }

  /**
   * Partition the list such that all elements less than E come before
   * all elements greater than E.
   * @param e the partition element
   */
  public LinkedList<E> partition(final E e, final Comparator<E> comparator) {

    //build two linked lists - one for smaller items and one for bigger
    Node<E> smaller = null;
    Node<E> smallerHead = null;
    Node<E> bigger = null;
    Node<E> biggerHead = null;

    Node<E> n = head;
    while (n != null) {
      final Node<E> next = n.next;
      n.next = null;
      if (comparator.compare(n.data, e) < 0) {
        if (smaller == null) {
          smaller = n;
          smallerHead = n;
        } else {
          smaller.next = n;
          smaller = n;
        }
      } else {
        if (bigger == null) {
          bigger = n;
          biggerHead = n;
        } else {
          bigger.next = n;
          bigger = n;
        }
      }
      n = next;
    }

    if (smallerHead == null) {
      return new LinkedList<>(biggerHead);
    }
    smaller.next = biggerHead;
    return new LinkedList<>(smallerHead);
  }

  /**
   * Reverses this list.
   */
  public void reverse() {
    Node<E> newHead = null;
    Node<E> n = head;
    while (n != null) {
      final Node<E> next = n.next;
      n.next = newHead;
      newHead = n;
      n = next;
    }
    head = newHead;
  }

  /**
   * Returns the middle element of the list.
   * Uses two runners - a fast and slow.
   * The fast runner iterates two steps at a time
   * and when it hits the end, the slow runner would have
   * hit the middle.
   *
   * @return the middle element
   */
  public E getMiddle() {
    Node<E> fast = head;
    Node<E> slow = head;
    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow.data;
  }

  /**
   * Tests if this list is a palindrome.
   * Is the reverse of the first half of the list equal
   * to the second half?
   * <p>
   * Reverses the first half of the list by using a stack.
   * Elements are pushed onto a stack until the midpoint of the list
   * is reached. Then, items are popped off the stack and
   * compared with the rest of the list.
   *
   * @return true if this list is a palindrome
   */
  public boolean isPalindrome() {
    final Stack<E> stack = new Stack<>();

    // fast/slow runner technique to get to the middle
    // of the list
    Node<E> fast = head;
    Node<E> slow = head;
    while (fast != null && fast.next != null) {
      stack.push(slow.data);
      fast = fast.next.next;
      slow = slow.next;
    }

    if (fast != null) {
      // there were an odd number of elements
      // so skip the middle one
      slow = slow.next;
    }

    while (!stack.isEmpty()) {
      final E top = stack.pop();
      if (!top.equals(slow.data)) {
        return false;
      }
      slow = slow.next;
    }
    return true;
  }

  /**
   * Detects if this list has a loop.
   * You have two iterators - the hare and the tortoise - one of them runs faster
   * than the other and, if the list has a loop, they will eventually collide.
   *
   * @return true if there is a loop, false otherwise
   */
  public boolean hasLoop() {
    Node<E> hare = head;
    Node<E> tortoise = head;
    while (hare != null && hare.next != null) {
      hare = hare.next.next;
      tortoise = tortoise.next;
      if (hare == tortoise) {
        return true;
      }
    }
    return false;
  }

  /**
   * Find the element at the beginning of the loop.
   * After colliding, if we keep the hare where it is (at the point of collision)
   * and move the tortoise back to the beginning of the list, and then move each of
   * them one step at a time, they will collide at the start of the loop.
   * http://fahdshariff.blogspot.co.uk/2016/11/detecting-loop-in-linked-list.html
   *
   * @return the element at the start of the loop
   */
  public E findLoopStart() {
    Node<E> tortoise = head;
    Node<E> hare = head;

    boolean hasLoop = false;
    while (hare != null && hare.next != null) {
      tortoise = tortoise.next;
      hare = hare.next.next;
      if (hare == tortoise) {
        hasLoop = true;
        break;
      }
    }

    if (hasLoop) {
      tortoise = head;
      while (tortoise != hare) {
        tortoise = tortoise.next;
        hare = hare.next;
      }
      return tortoise.data;
    }
    return null;
  }

  /**
   * Checks if this list intersects with another (i.e. has a common element)
   * and returns the intersection point.
   *
   * @param other the other list
   * @return intersection element or null if they don't intersect
   */
  public E getIntersection(final LinkedList<E> other) {

    // two lists intersect if they have the same tail element.
    if (getTail() != other.getTail()) {
      return null;
    }

    // determine which list is longer
    final int size1 = size();
    final int size2 = other.size();
    // (TODO: combine the getTail and size methods so we don't have to iterate twice)

    final LinkedList<E> longer = size2 > size1 ? other : this;
    final LinkedList<E> shorter = longer == this ? other : this;
    final int stepsAhead = Math.abs(size1 - size2);

    // set up two runners to run along the lists
    // on the longer list, advance its runner by the difference in lengths
    Node<E> m = shorter.head;
    Node<E> n = longer.head;
    for (int i = 0; i < stepsAhead; i++) {
      n = n.next;
    }

    // now iterate until they collide
    while (n != m) {
      n = n.next;
      m = m.next;
    }

    return n.data;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append('[');
    Node<E> n = head;
    while (n != null) {
      sb.append(n.data);
      if (n.next != null) {
        sb.append(',').append(' ');
      }
      n = n.next;
    }
    sb.append(']');
    return sb.toString();
  }
}
