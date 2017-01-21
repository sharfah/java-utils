package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.sharfah.util.collections.LinkedList.Node;

public class LinkedListTest {

  @Test
  public void testAdd() {
    final LinkedList<String> list = new LinkedList<>();
    assertThat(list.isEmpty(), is(true));
    list.add("foo");
    assertThat(list.get(0), is("foo"));
    list.add("bar");
    assertThat(list.get(0), is("bar"));
    assertThat(list.get(1), is("foo"));
  }

  @Test
  public void testIsEmpty() {
    final LinkedList<String> list = new LinkedList<>();
    assertThat(list.isEmpty(), is(true));
    list.add("foo");
    list.add("bar");
    assertThat(list.isEmpty(), is(false));
  }

  @Test
  public void testDelete() {
    final LinkedList<String> list = new LinkedList<>();
    assertThat(list.delete("bar"), is(false));
    list.add("foo");
    assertThat(list.delete("bar"), is(false));
    assertThat(list.delete("foo"), is(true));
    assertThat(list.isEmpty(), is(true));
    list.add("bar");
    list.add("baz");
    assertThat(list.delete("bar"), is(true));
    assertThat(list.delete("foo"), is(false));
    assertThat(list.delete("baz"), is(true));
    assertThat(list.isEmpty(), is(true));
  }

  @Test
  public void testContains() {
    final LinkedList<String> list = new LinkedList<>();
    assertFalse(list.contains("foo"));
    list.add("foo");
    list.add("bar");
    list.add("baz");
    assertTrue(list.contains("foo"));
    assertTrue(list.contains("bar"));
    assertTrue(list.contains("baz"));
  }

  @Test
  public void testSize() {
    final LinkedList<String> list = new LinkedList<>();
    assertThat(list.size(), is(0));
    list.add("foo");
    assertThat(list.size(), is(1));
    list.add("bar");
    list.add("baz");
    assertThat(list.size(), is(3));

  }

  @Test
  public void testRemoveDuplicatesInPlace() {
    final LinkedList<String> list = new LinkedList<>();
    list.add("foo");
    list.add("bar");
    list.add("foo");
    list.add("baz");
    list.add("foo");
    list.add("baz");
    list.removeDuplicatesInPlace();
    assertThat(list.size(), is(3));
    assertTrue(list.contains("foo"));
    assertTrue(list.contains("bar"));
    assertTrue(list.contains("baz"));
  }

  @Test
  public void testRemoveDuplicates() {
    final LinkedList<String> list = new LinkedList<>();
    list.add("foo");
    list.add("bar");
    list.add("foo");
    list.add("baz");
    list.add("foo");
    list.add("baz");
    list.removeDuplicates();
    assertThat(list.size(), is(3));
    assertTrue(list.contains("foo"));
    assertTrue(list.contains("bar"));
    assertTrue(list.contains("baz"));
  }

  @Test
  public void testFindKthToLastElement() {
    final LinkedList<String> list = new LinkedList<>();
    list.appendToTail("foo");
    list.appendToTail("bar");
    list.appendToTail("baz");
    assertThat(list.findKthToLastElement(2), is("bar"));
    assertThat(list.findKthToLastElement(3), is("foo"));
    assertThat(list.findKthToLastElement(1), is("baz"));
  }

  @Test
  public void testGet() {
    final LinkedList<Integer> list = new LinkedList<>();
    list.appendToTail(10);
    list.appendToTail(3);
    list.appendToTail(4);
    list.appendToTail(9);
    list.appendToTail(5);
    list.appendToTail(8);
    assertThat(list.get(0), is(10));
    assertThat(list.get(5), is(8));
  }

  @Test
  public void testPartition() {
    LinkedList<Integer> list = new LinkedList<>();
    list.appendToTail(10);
    list.appendToTail(3);
    list.appendToTail(4);
    list.appendToTail(9);
    list.appendToTail(5);
    list.appendToTail(8);
    LinkedList<Integer> result = list.partition(5, Integer::compareTo);
    assertThat(result.get(0), is(3));
    assertThat(result.get(1), is(4));
    assertThat(result.get(2), is(10));
    assertThat(result.get(3), is(9));
    assertThat(result.get(4), is(5));
    assertThat(result.get(5), is(8));

    list = new LinkedList<>();
    list.appendToTail(10);
    list.appendToTail(3);
    list.appendToTail(4);
    list.appendToTail(9);
    list.appendToTail(5);
    list.appendToTail(8);
    result = list.partition(0, Integer::compareTo);
    assertThat(result.get(0), is(10));
    assertThat(result.get(1), is(3));
    assertThat(result.get(2), is(4));
    assertThat(result.get(3), is(9));
    assertThat(result.get(4), is(5));
    assertThat(result.get(5), is(8));
  }

  @Test
  public void testReverse() {
    LinkedList<Integer> list = new LinkedList<>();
    list.appendToTail(10);
    list.appendToTail(3);
    list.appendToTail(4);
    list.reverse();
    assertThat(list.get(0), is(4));
    assertThat(list.get(1), is(3));
    assertThat(list.get(2), is(10));

    list = new LinkedList<>();
    list.add(10);
    list.reverse();
    assertThat(list.get(0), is(10));
  }

  @Test
  public void testGetMiddle() {
    final LinkedList<Integer> list = new LinkedList<>();
    list.appendToTail(10);
    list.appendToTail(3);
    list.appendToTail(4);
    assertThat(list.getMiddle(), is(3));
    list.appendToTail(5);
    assertThat(list.getMiddle(), is(4));
  }

  @Test
  public void isPalindrome() {
    LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(3);
    list.add(1);
    assertTrue(list.isPalindrome());

    list = new LinkedList<>();
    list.add(2);
    list.add(3);
    list.add(1);
    assertFalse(list.isPalindrome());

    list = new LinkedList<>();
    list.add(1);
    list.add(3);
    list.add(3);
    list.add(1);
    assertTrue(list.isPalindrome());

    list = new LinkedList<>();
    list.add(1);
    list.add(3);
    list.add(2);
    list.add(1);
    assertFalse(list.isPalindrome());

    list = new LinkedList<>();
    list.add(1);
    assertTrue(list.isPalindrome());
  }

  @Test
  public void testHasLoop() {
    final LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    assertFalse(list.hasLoop());

    final Node<Integer> n1 = new Node<>(1);
    final Node<Integer> n2 = new Node<>(2);
    final Node<Integer> n3 = new Node<>(3);
    n1.next = n2;
    n2.next = n3;
    n3.next = n2;
    assertTrue(new LinkedList<Integer>(n1).hasLoop());
  }

  @Test
  public void testFindLoopStart() {
    final LinkedList<Integer> list = new LinkedList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    assertNull(list.findLoopStart());

    final Node<Integer> n1 = new Node<>(1);
    final Node<Integer> n2 = new Node<>(2);
    final Node<Integer> n3 = new Node<>(3);
    n1.next = n2;
    n2.next = n3;
    n3.next = n2;
    assertThat(new LinkedList<Integer>(n1).findLoopStart(), is(2));
  }

  @Test
  public void testGetIntersection() {
    final Node<Integer> n1 = new Node<>(1);
    final Node<Integer> n2 = new Node<>(2);
    final Node<Integer> n3 = new Node<>(3);
    final Node<Integer> n4 = new Node<>(4);
    final Node<Integer> n5 = new Node<>(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    final LinkedList<Integer> list1 = new LinkedList<Integer>(n1);

    final Node<Integer> n6 = new Node<>(6);
    final Node<Integer> n7 = new Node<>(7);
    final Node<Integer> n8 = new Node<>(8);
    final Node<Integer> n9 = new Node<>(9);
    n6.next = n7;
    n7.next = n8;
    n8.next = n9;
    final LinkedList<Integer> list2 = new LinkedList<Integer>(n6);

    assertNull(list1.getIntersection(list2));

    n9.next = n3;

    assertThat(list1.getIntersection(list2), is(3));
  }

}
