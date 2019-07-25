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
package jetbrains.mps.typesystem.inference.util;

import jetbrains.mps.lang.pattern.util.IMatchModifier;
import jetbrains.mps.lang.pattern.util.MatchingUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.*;

public class StructuralNodeSet<T> implements Set<SNode> {
  private Set<SNodeWrapper> myWrappers = new LinkedHashSet<>();

  public StructuralNodeSet() {
  }

  public StructuralNodeSet(StructuralNodeSet<T> set) {
    myWrappers.addAll(set.myWrappers);
  }

  public StructuralNodeSet(Collection<? extends SNode> collection) {
    addAll(collection);
  }

  public boolean containsStructurally(SNode ourNode) {
    return myWrappers.contains(new SNodeWrapper(ourNode));
  }

  public boolean removeStructurally(SNode ourNode) {
    return myWrappers.remove(new SNodeWrapper(ourNode));
  }

  public boolean addStructurally(SNode ourNode) {
    return myWrappers.add(new SNodeWrapper(ourNode));
  }

  public boolean addCollectionStructurally(Collection<? extends SNode> ourNodes) {
    boolean result = false;
    if (ourNodes == null) return false;
    for (SNode ourNode : ourNodes) {
      boolean someResult = addStructurally(ourNode);
      result = result || someResult;
    }
    return result;
  }

  public boolean addAllStructurally(StructuralNodeSet ourNodes) {
    return myWrappers.addAll(ourNodes.myWrappers);
  }

  public void putAllStructurally(StructuralNodeSet<T> ourNodes) {
    myWrappers.addAll(ourNodes.myWrappers);
  }

  @Override
  public int size() {
    return myWrappers.size();
  }

  @Override
  public boolean isEmpty() {
    return myWrappers.isEmpty();
  }

  @Override
  public void clear() {
    myWrappers.clear();
  }

  @Override
  public boolean contains(Object o) {
    if (!(o instanceof SNode)) return false;
    return containsStructurally((SNode) o);
  }

  @NotNull
  @Override
  public Iterator<SNode> iterator() {
    return getNodes().iterator();
  }

  private List<SNode> getNodes() {
    List<SNode> nodes = new ArrayList<>();
    for (SNodeWrapper w : myWrappers) {
      nodes.add(w.myNode);
    }
    return nodes;
  }

  @Override
  public boolean add(SNode o) {
    return addStructurally(o);
  }

  @Override
  public boolean addAll(@NotNull Collection<? extends SNode> c) {
    return addCollectionStructurally(c);
  }


  @Override
  public boolean remove(Object o) {
    if (!(o instanceof SNode)) return false;
    return removeStructurally((SNode) o);
  }


  @NotNull
  @Override
  public Object[] toArray() {
    return getNodes().toArray();
  }

  @NotNull
  @Override
  public <T> T[] toArray(@NotNull T[] a) {
    return getNodes().toArray(a);
  }

  @Override
  public boolean containsAll(@NotNull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean retainAll(@NotNull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean removeAll(@NotNull Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  public void addAndRewrite(SNode node) {
    if (!containsStructurally(node)) {
      myWrappers.add(new SNodeWrapper(node));
    } else {
      SNodeWrapper wrapper = new SNodeWrapper(node);
      myWrappers.remove(wrapper);
      myWrappers.add(wrapper);
    }
  }

  private static class SNodeWrapper {
    private SNode myNode;
    private int myHashCode;


    private SNodeWrapper(SNode node) {
      myNode = node;
      myHashCode = MatchingUtil.hash(myNode);
    }

    @Override
    public int hashCode() {
      return myHashCode;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof SNodeWrapper)) return false;
      SNodeWrapper wrapper = (SNodeWrapper) obj;
      return MatchingUtil.matchNodes(wrapper.myNode, myNode, IMatchModifier.DEFAULT, false);
    }

  }
}
