/*
 * Copyright 2003-2017 JetBrains s.r.o.
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
package jetbrains.mps.smodel.runtime;

import jetbrains.mps.util.IterableUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractConcept;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SInterfaceConcept;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Returns all parents of a concept or concept interface in "inheritance" order.
 * "Inheritance" order means that behavior methods, constraints and some other concept stuff, that is inherited,
 * should be searched accordingly to order of parents in this list
 *
 * FIXME there are {@link org.jetbrains.mps.util.BreadthConceptHierarchyIterator} and {@link org.jetbrains.mps.util.DepthFirstConceptIterator} that walk
 * complete hierarchy right away, so there's no need for implicit recursion of BaseConstraintsDescriptor's #collectParents, #getDescriptor and ConceptRegistry
 * that ends up with another BaseConstraintsDescriptor.
 * FIXME There's SModelUtil.getDirectSuperConcepts() with identical logic.
 */
public class InheritanceIterable implements Iterable<SAbstractConcept> {
  private ArrayList<SAbstractConcept> myParents = new ArrayList<>(4);

  public InheritanceIterable(SAbstractConcept concept) {
    if (concept instanceof SConcept) {
      SConcept sc = ((SConcept) concept).getSuperConcept();
      if (sc != null) {
        myParents.add(sc);
      }
      myParents.addAll(IterableUtil.asCollection(((SConcept) concept).getSuperInterfaces()));
    } else{
      myParents.addAll(IterableUtil.asCollection(((SInterfaceConcept) concept).getSuperInterfaces()));
    }
  }

  @NotNull
  @Override
  public Iterator<SAbstractConcept> iterator() {
    return myParents.iterator();
  }

  public final Stream<SAbstractConcept> stream() {
    return StreamSupport.stream(spliterator(), false);
  }
}
