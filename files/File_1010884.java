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
package jetbrains.mps.idea.java.index;

import jetbrains.mps.smodel.SNodePointer;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * Holds index-related information about nodes and their association links in a model.
 * <p/>
 * Implementation note: it's essential to implement equals/hashCode to use this class as Value for c.i.util.indexing.FileBasedIndexExtension
 * @author Artem Tikhomirov
 */
@Immutable
public final class NodeAssociationsData {
  private final SModelReference myModelRef;
  private final SNodeId[] myNodes;
  private final SReferenceLink[] myAssociations;

  public NodeAssociationsData(@NotNull SModelReference modelRef, @NotNull List<Pair<SNodeId, SReferenceLink>> pairs) {
    myModelRef = modelRef;
    myNodes = new SNodeId[pairs.size()];
    myAssociations = new SReferenceLink[pairs.size()];

    for (int i = 0; i < myNodes.length; i++) {
      final Pair<SNodeId, SReferenceLink> p = pairs.get(i);
      myNodes[i] = p.o1;
      myAssociations[i] = p.o2;
    }
  }

  // client code access
  public void forEach(BiConsumer<SNodeReference, SReferenceLink> action) {
    for (int i = 0; i < myNodes.length; i++) {
      action.accept(new SNodePointer(myModelRef, myNodes[i]), myAssociations[i]);
    }
  }

  // serialization support
  /*package*/ SModelReference modelRef() {
    return myModelRef;
  }

  // serialization support
  /*package*/ List<Pair<SNodeId, SReferenceLink>> pairs() {
    ArrayList<Pair<SNodeId, SReferenceLink>> rv = new ArrayList<>(myNodes.length);
    for (int i = 0; i < myNodes.length; i++) {
      rv.add(new Pair<>(myNodes[i], myAssociations[i]));
    }
    return rv;
  }

  @Override
  public int hashCode() {
    return myModelRef.hashCode() * 37 + Arrays.hashCode(myNodes) + Arrays.hashCode(myAssociations);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj.getClass() != getClass()) {
      return false;
    }
    NodeAssociationsData o = (NodeAssociationsData) obj;
    return myModelRef.equals(o.myModelRef) && Arrays.equals(myNodes, o.myNodes) && Arrays.equals(myAssociations, o.myAssociations);
  }
}
