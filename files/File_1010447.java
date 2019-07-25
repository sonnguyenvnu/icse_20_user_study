/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package org.jetbrains.mps.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SInterfaceConcept;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Iterates hierarchy of concepts, breadth-first. Iterator doesn't support removals.
 * Return values are not unique, e.g. if an interface is implemented by few concepts, it is reported several times.
 * Wrap with {@link org.jetbrains.mps.util.UniqueIterator} if necessary.
 *
 * Given
 *  ConceptA implements I1, I2 and
 *  ConceptB extends ConceptA implements I3, I4;
 *  Interface I3 extends I5;
 *  Interface I5 extends I1;
 *
 *  and ConceptB as starting point, yields:
 *   { ConceptB, I3, I4 } - level 0 for ConceptB, { ConceptA, I1, I2 } - level 1 from ConceptA , { I5 } level 1 from I3, {I1} - level 2 from I5.
 *
 * Note, this class is similar to {@link org.jetbrains.mps.openapi.language.SConceptUtil}, except for explicit walk algorithm and OOP-friendly style.
 *
 * @author Artem Tikhomirov
 */
public class BreadthConceptHierarchyIterator implements Iterable<SAbstractConcept>, Iterator<SAbstractConcept>{
  private final Deque<SAbstractConcept> myQueue = new ArrayDeque<>();
  private final SAbstractConcept myStart;

  public BreadthConceptHierarchyIterator(@NotNull SAbstractConcept start) {
    myStart = start;
  }

  @NotNull
  @Override
  public Iterator<SAbstractConcept> iterator() {
    myQueue.clear();
    myQueue.add(myStart);
    return this;
  }

  @Override
  public boolean hasNext() {
    return !myQueue.isEmpty();
  }

  @Override
  public SAbstractConcept next() {
    SAbstractConcept rv = myQueue.removeFirst();
    final Iterable<SInterfaceConcept> superInterfaces;
    final SConcept parentConcept;
    if (rv instanceof SInterfaceConcept) {
      superInterfaces = ((SInterfaceConcept) rv).getSuperInterfaces();
      parentConcept = null;
    } else {
      assert rv instanceof SConcept;
      parentConcept = ((SConcept) rv).getSuperConcept();
      superInterfaces = ((SConcept) rv).getSuperInterfaces();
    }
    // for a concept, implemented interfaces  are deemed as 'this' level, while extended concept constitutes jump to a next depth
    for (SAbstractConcept i : superInterfaces) {
      myQueue.addLast(i);
    }
    if (parentConcept != null) {
      myQueue.addLast(parentConcept);
    }
    return rv;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
