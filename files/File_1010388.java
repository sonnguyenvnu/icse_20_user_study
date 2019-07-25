/*
 * Copyright 2003-2011 JetBrains s.r.o.
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
package jetbrains.mps.util.iterable;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

/**
 * Translate each element of first sequence to sequence of another elements, and serve these through {@link Iterator}
 */
public abstract class CollectManyIterator<IN, T> implements Iterable<T>, Iterator<T> {

  private final Iterable<IN> myStart;
  private Iterator<IN> myCurrentOuter;
  private Iterator<T> myCurrentInner;
  private T myNext;

  public CollectManyIterator(@NotNull Iterable<IN> input) {
    myStart = input;
    reset(); // just in case it's used as Iterator, not Iterable, i.e. without #iterator() call
  }

  private void reset() {
    myCurrentOuter = myStart.iterator();
    myNext = nextInternal();
  }

  @Override
  public boolean hasNext() {
    return myNext != null;
  }

  @Override
  public T next() {
    T curr = myNext;
    myNext = nextInternal();
    return curr;
  }

  private T nextInternal() {
    if (myCurrentInner != null && myCurrentInner.hasNext()) {
      return myCurrentInner.next();
    }
    while (myCurrentOuter.hasNext()) {
      IN element = myCurrentOuter.next();
      myCurrentInner = element != null ? translate(element) : null;
      if (myCurrentInner != null && myCurrentInner.hasNext()) {
        return myCurrentInner.next();
      }
    }
    return null;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  @Nullable
  protected abstract Iterator<T> translate(IN node);

  @NotNull
  @Override
  public Iterator<T> iterator() {
    reset();
    return this;
  }
}
