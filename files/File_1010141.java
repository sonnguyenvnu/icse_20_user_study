/*
 * Copyright 2003-2018 JetBrains s.r.o.
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
package jetbrains.mps.generator.impl.dependencies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.SortedSet;

/**
 * Array-backed implementation of {@link SortedSet}, ordered by natural order of {@link Comparable} elements.
 * Note, elements shall respect {@code compareTo(Object other)} case, when {@code other} not necessarily instance of kind {@code <E>}.
 * @author Artem Tikhomirov
 */
/*package*/ final class SortedArraySet<E extends Comparable<Object>> extends AbstractSet<E> implements SortedSet<E> {
  private final List<E> myData;

  public SortedArraySet() {
    myData = new ArrayList<>();
  }

  public SortedArraySet(int initialSize) {
    myData = new ArrayList<>(initialSize);
  }

  public SortedArraySet(Collection<? extends E> c) {
    myData = new ArrayList<>(c.size());
    addAll(c);
  }

  private SortedArraySet(List<E> subList, int dummy) {
    myData = subList;
  }

  public E getOrAdd(E e) {
    final int i = Collections.binarySearch(myData, e);
    if (i < 0) {
      myData.add(-i - 1, e);
      return e;
    } else {
      return myData.get(i);
    }
  }

  // override some AbstractSet operations for effectiveness

  @Override
  public boolean add(E e) {
    final int i = Collections.binarySearch(myData, e);
    if (i < 0) {
      myData.add(-i - 1, e);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean remove(Object o) {
    final int i = Collections.binarySearch(myData, o);
    if (i >= 0) {
      myData.remove(i);
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(Object o) {
    return Collections.binarySearch(myData, o) >= 0;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return myData.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return myData.retainAll(c);
  }

  @Override
  public void clear() {
    myData.clear();
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public Object[] toArray() {
    return myData.toArray();
  }

  @SuppressWarnings("NullableProblems")
  @Override
  public <T> T[] toArray(@NotNull T[] a) {
    return myData.toArray(a);
  }

  @Override
  public boolean isEmpty() {
    return myData.isEmpty();
  }



  // implement the rest of necessary methods

  @SuppressWarnings("NullableProblems")
  @Override
  public Iterator<E> iterator() {
    return myData.iterator();
  }

  @Override
  public int size() {
    return myData.size();
  }

  @Nullable
  @Override
  public Comparator<? super E> comparator() {
    // no external comparator yet
    return null;
  }

  @Override
  public SortedSet<E> subSet(E fromElement, E toElement) {
    final int from = myData.indexOf(fromElement);
    if (from == -1) {
      throw new IllegalArgumentException();
    }
    final int to = myData.indexOf(toElement);
    if (to == -1) {
      throw new IllegalArgumentException();
    }
    if (from > to) {
      throw new IllegalArgumentException();
    }
    return new SortedArraySet<>(myData.subList(from, to), 0);
  }

  @Override
  public SortedSet<E> headSet(E toElement) {
    final int to = myData.indexOf(toElement);
    if (to == -1) {
      throw new IllegalArgumentException();
    }
    return new SortedArraySet<>(myData.subList(0, to), 0);
  }

  @Override
  public SortedSet<E> tailSet(E fromElement) {
    final int from = myData.indexOf(fromElement);
    if (from == -1) {
      throw new IllegalArgumentException();
    }
    return new SortedArraySet<>(myData.subList(from, myData.size()), 0);
  }

  @Override
  public E first() {
    if (myData.isEmpty()) {
      throw new NoSuchElementException();
    }
    return myData.get(0);
  }

  @Override
  public E last() {
    if (myData.isEmpty()) {
      throw new NoSuchElementException();
    }
    return myData.get(myData.size()-1);
  }
}
