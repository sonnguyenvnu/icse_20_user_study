/*
 * Copyright 2003-2019 JetBrains s.r.o.
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
package jetbrains.mps.workbench.index;

import gnu.trove.THashSet;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author Artem Tikhomirov
 * @since 2019.2
 */
public final class ModelNodesData {
  private final THashSet<SNodeId> myElements;

  public ModelNodesData(Collection<SNodeId> elements) {
    myElements = new THashSet<>(elements);
  }

  /*package*/ int count() {
    return myElements.size();
  }

  /*package*/ Iterable<SNodeId> elements() {
    return myElements;
  }

  @Override
  public int hashCode() {
    return myElements.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ModelNodesData) {
      return myElements.equals(((ModelNodesData) obj).myElements);
    }
    return false;
  }

  /*package*/ ModelNodesData intersect(ModelNodesData other) {
    ArrayList<SNodeId> l = new ArrayList<>(myElements.size());
    for (SNodeId nid : myElements) {
      if (other.myElements.contains(nid)) {
        l.add(nid);
      }
    }
    return new ModelNodesData(l);
  }
}
