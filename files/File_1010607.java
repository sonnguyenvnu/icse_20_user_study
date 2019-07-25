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
package jetbrains.mps.smodel;

import jetbrains.mps.smodel.references.UnregisteredNodes;
import jetbrains.mps.util.EqualUtil;
import jetbrains.mps.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.annotations.Immutable;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;
import org.jetbrains.mps.openapi.model.SNodeReference;
import org.jetbrains.mps.openapi.module.SRepository;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade;
import org.jetbrains.mps.openapi.persistence.PersistenceFacade.IncorrectNodeIdFormatException;

@Immutable
public class SNodePointer implements SNodeReference {
  @Nullable
  private final SModelReference myModelReference;
  @Nullable
  private final SNodeId myNodeId;

  public SNodePointer(String modelUID, String nodeId) {
    this(PersistenceFacade.getInstance().createModelReference(modelUID), PersistenceFacade.getInstance().createNodeId(nodeId));
  }

  public SNodePointer(SNode node) {
    if (node == null || node.getModel() == null) {
      myModelReference = null;
      myNodeId = null;
      return;
    }
    myModelReference = node.getModel().getReference();
    myNodeId = node.getNodeId();
  }

  public SNodePointer(@Nullable SModelReference modelReference, @Nullable SNodeId nodeId) {
    myModelReference = modelReference;
    myNodeId = nodeId;
  }

  @Nullable
  @Override
  public SNode resolve(SRepository repo) {
    if (myNodeId == null) return null;

    if (myModelReference != null) {

      SModel model = myModelReference.resolve(repo);
      if (model != null) {
        SNode node = model.getNode(myNodeId);
        if (node != null) {
          return node;
        }
      }
    }

    UnregisteredNodes unregisteredNodes = UnregisteredNodes.instance();
    if (unregisteredNodes != null) {
      return unregisteredNodes.get(myModelReference, myNodeId);
    }
    return null;
  }

  @Nullable
  @Override
  public SModelReference getModelReference() {
    return myModelReference;
  }

  public String toString() {
    return SNodePointer.serialize(this);
  }

  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SNodePointer)) return false;

    SNodePointer np = (SNodePointer) o;
    return EqualUtil.equals(myModelReference, np.myModelReference) && EqualUtil.equals(myNodeId, np.myNodeId);
  }

  public int hashCode() {
    int sum = 0;
    if (myModelReference != null) {
      sum += myModelReference.hashCode();
    }
    if (myNodeId != null) {
      sum += 11 * myNodeId.hashCode();
    }
    return sum;
  }

  public static String serialize(SNodeReference p) {
    SNodePointer np = (SNodePointer) p;
    return np.myModelReference + "/" + StringUtil.escapeRefChars(String.valueOf(np.myNodeId));
  }

  public static SNodeReference deserialize(@NotNull String from) {
    int delimiterIndex = from.lastIndexOf('/');
    if (delimiterIndex < 0) {
      throw new IncorrectNodeIdFormatException("No delimiter discovered in the passed argument " + from);
    }
    String nodeId = StringUtil.unescapeRefChars(from.substring(delimiterIndex + 1));
    SNodeId sNodeId;
    if (String.valueOf((Object) null).equals(nodeId)) {
      // supporting myNodeId == null serialized to string
      sNodeId = null;
    } else {
      sNodeId = PersistenceFacade.getInstance().createNodeId(nodeId);
    }

    String modelReference = from.substring(0, delimiterIndex);
    SModelReference sModelReference;
    if (String.valueOf((Object) null).equals(modelReference)) {
      // supporting myModelReference == null serialized to string
      sModelReference = null;
    } else {
      sModelReference = PersistenceFacade.getInstance().createModelReference(modelReference);
    }

    return new jetbrains.mps.smodel.SNodePointer(sModelReference, sNodeId);
  }

  @Nullable
  @Override
  public SNodeId getNodeId() {
    return myNodeId;
  }
}
