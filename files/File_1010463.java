/*
 * Copyright 2003-2015 JetBrains s.r.o.
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
package jetbrains.mps.persistence.binary;

import jetbrains.mps.smodel.InterfaceSNode;
import jetbrains.mps.util.io.ModelInputStream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.language.SConcept;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SModelReference;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.io.IOException;
import java.util.Collection;

public final class NodesReader extends BareNodeReader {
  private final ReadHelper myReadHelper;
  private boolean hasSkippedNodes = false;
  private Collection<SNodeId> myExternalRefs;
  private Collection<SNodeId> myLocalRefs;

  public NodesReader(@NotNull SModelReference modelReference, @NotNull ModelInputStream is, ReadHelper readHelper) {
    super(modelReference, is);
    myReadHelper = readHelper;
  }

  /*package*/ void collectExternalTargets(@Nullable Collection<SNodeId> store) {
    myExternalRefs = store;
  }
  /*package*/ void collectLocalTargets(@Nullable Collection<SNodeId> store) {
    myLocalRefs = store;
  }

  public boolean hasSkippedNodes() {
    return hasSkippedNodes;
  }

  @Override
  protected SNode instantiate(@Nullable SNode parent) throws IOException {
    SConcept concept = myReadHelper.readConcept(myIn.readShort());
    SNodeId nodeId = myIn.readNodeId();
    SContainmentLink link = myReadHelper.readAggregation(myIn.readShort());

    boolean interfaceNode = false;
    if (myReadHelper.isRequestedInterfaceOnly()) {
      interfaceNode = myReadHelper.isInterface(concept) || link == null;
    }
    // TODO report if (nodeInfo != 0 && myEnv != null) .. myEnv.nodeRoleRead/conceptRead();

    jetbrains.mps.smodel.SNode node = interfaceNode ? new InterfaceSNode(concept, nodeId) : new jetbrains.mps.smodel.SNode(concept, nodeId);

    if (parent == null) {
      return node;
    }
    if (!(parent instanceof InterfaceSNode) || node instanceof InterfaceSNode) {
      parent.addChild(link, node);
    } else {
      ((InterfaceSNode) parent).skipRole(link);
      hasSkippedNodes = true;
    }
    return node;
  }

  @Override
  protected void localNodeReferenceRead(SNodeId nodeId) {
    if (nodeId != null && myLocalRefs != null) {
      myLocalRefs.add(nodeId);
    }
  }

  @Override
  protected void externalNodeReferenceRead(SModelReference targetModel, SNodeId targetNodeId) {
    if (targetNodeId != null && myExternalRefs != null) {
      myExternalRefs.add(targetNodeId);
    }
  }

  @Override
  protected void readReferences(SNode node) throws IOException {
    int refs = myIn.readShort();
    while (refs-- > 0) {
      final SReferenceLink link = myReadHelper.readAssociation(myIn.readShort());
      readReference(link, node);
    }
  }

  @Override
  protected void readProperties(SNode node) throws IOException {
    int properties = myIn.readShort();
    while (properties-- > 0) {
      final SProperty property = myReadHelper.readProperty(myIn.readShort());
      final String value = myIn.readString();
      node.setProperty(property, value);
    }
  }
}
