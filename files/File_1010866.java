/*
 * Copyright 2003-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jetbrains.mps.idea.core.psi.impl;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
* Created with IntelliJ IDEA.
* User: fyodor
* Date: 2/14/13
* Time: 12:53 PM
* To change this template use File | Settings | File Templates.
*/
/*package*/ class NodeList {
  private MPSPsiNodeBase owner;
  private Entry head;
  private int size = 0;

  NodeList(MPSPsiNodeBase owner) {
    this.owner = owner;
  }

  MPSPsiNodeBase owner() {
    return owner;
  }

  MPSPsiNodeBase first() {
    return head != null ? head.node : null;
  }

  MPSPsiNodeBase last() {
    return head != null && head.prev != null ? head.prev.node : null;
  }

  MPSPsiNodeBase next(MPSPsiNodeBase prev) {
    assert prev.getEntry().list() == this;

    return prev.getEntry().next.node;
  }

  MPSPsiNodeBase prev(MPSPsiNodeBase prev) {
    assert prev.getEntry().list() == this;

    return prev.getEntry().prev.node;
  }

  int size () {
    return size;
  }

  void toArray(PsiElement[] array) {
    Entry e = head;
    if (e == null) return;
    int i = 0;
    do {
      array[i++] = e.node;
      e = e.next;
    } while (e != head);
  }

  void addFirst (@NotNull MPSPsiNodeBase node) {
    assert node.getEntry() == null;

    Entry newHead = new Entry(node);
    if (head == null) {
      head = newHead;
      head.next = head.prev = head;
    }
    else {
      insertBetween(newHead, head.prev, head);
      head = newHead;
    }
    size++;
  }

  void addLast (@NotNull MPSPsiNodeBase node) {
    assert node.getEntry() == null;

    Entry newTail = new Entry(node);
    if (head == null) {
      head = newTail;
      head.next = head.prev = head;
    }
    else {
      insertBetween(newTail, head.prev, head);
    }
    size++;
  }

  void insertAfter(@NotNull MPSPsiNodeBase anchor, @NotNull MPSPsiNodeBase node) {
    assert anchor != null && anchor.getEntry() != null && anchor.getEntry().list() == this;
    assert node.getEntry() == null;

    Entry toInsert = new Entry(node);
    insertBetween(toInsert, anchor.getEntry(), anchor.getEntry().next);
    size++;
  }

  void insertBefore(@NotNull MPSPsiNodeBase anchor, @NotNull MPSPsiNodeBase node) {
    assert anchor.getEntry() != null && anchor.getEntry().list() == this;
    assert node.getEntry() == null;

    Entry toInsert = new Entry(node);
    insertBetween(toInsert, anchor.getEntry().prev, anchor.getEntry());
    size++;
  }

  void remove (@NotNull MPSPsiNodeBase node) {
    assert node.getEntry().list() == this;

    Entry toRemove = node.getEntry();

    if (toRemove.next != toRemove) {
      toRemove.next.prev = toRemove.prev;
      toRemove.prev.next = toRemove.next;
      if (head == toRemove) {
        head = toRemove.next;
      }
    }
    else {
      assert head == toRemove;
      head = null;
    }
    toRemove.clear();
    size--;
  }

  void replace (@NotNull MPSPsiNodeBase old, @NotNull MPSPsiNodeBase replacement) {
    assert old.getEntry().list() == this;
    assert replacement.getEntry() == null;

    Entry entry = old.getEntry();
    old.setEntry(null);
    replacement.setEntry(entry);
    entry.node = replacement;
  }

  private void insertBetween(Entry toInsert, Entry after, Entry before) {
    toInsert.prev = after;
    toInsert.next = before;
    after.next = toInsert;
    before.prev = toInsert;
  }

  void clear () {
    Entry e = head;
    if (e == null) return;
    do {
      Entry next = e.next;
      e.clear();
      e = next;
    } while (e != head);
    head = null;
    size = 0;
  }

  Entry findEntry(@NotNull MPSPsiNodeBase node) {
    Entry e = head;
    if (e == null) return null;
    do {
      Entry next = e.next;
      if(e.node.equals(node))
        return e;
      e = next;
    } while (e != head);
    return null;
  }

  class Entry {
    private Entry next;
    private Entry prev;
    private MPSPsiNodeBase node;

    Entry (MPSPsiNodeBase node) {
      this.node = node;
      node.setEntry(this);
    }

    void clear () {
      node.setEntry(null);
      node = null;
      next = null;
      prev = null;
    }

    public NodeList list() {
      return NodeList.this;
    }

    boolean isFirst() {
      return NodeList.this.head == this;
    }

    boolean isLast () {
      return NodeList.this.head != null && NodeList.this.head.prev == this;
    }

    boolean isAttached () {
      return NodeList.this.owner != null;
    }
  }

}
