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
package jetbrains.mps.smodel.loading;

import jetbrains.mps.extapi.model.SModelData;
import jetbrains.mps.smodel.InterfaceSNode;
import jetbrains.mps.smodel.LazySNode;
import jetbrains.mps.util.IterableUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeId;

import java.util.Iterator;

/**
 * Takes partially-loaded model and fills it with nodes from a fully-loaded counterpart.
 * Preserves instances of original partially-loaded model as they might be already exposed to user code
 * (i.e. full load triggered as part of model traversal, with partial root already accessed by a client).
 * Fully-loaded model is not preserved.
 * SHALL MOVE TO [smodel] once LazySNode and InterfaceSNode move there (either with SNode or as interfaces).
 */
public class PartialModelUpdateFacility {

  private static final Logger LOG = LogManager.getLogger(PartialModelUpdateFacility.class);

  private SModelData myModel;
  private SModelData myFullModel;
  private final SModel myDataOwner;

  /**
   *
   * @param model model being updated, survives.
   * @param fullModel source of nodes to inject into updated models, for disposal.
   * @param dataOwner provides extra information about location in case anything goes wrong
   */
  public PartialModelUpdateFacility(@NotNull SModelData model, @NotNull SModelData fullModel, @NotNull SModel dataOwner) {
    myModel = model;
    myFullModel = fullModel;
    myDataOwner = dataOwner;
  }

  /**
   * Update initial model with elements from full model. Instance of initial model is the one to keep.
   */
  public void update() {
    for (SNode root : myModel.getRootNodes()) {
      if (root instanceof LazySNode) {
        SNode fullRoot = myFullModel.getNode(root.getNodeId());
        if (fullRoot == null) continue; //this can happen after model update if the
        for (SNode child : IterableUtil.copyToList(fullRoot.getChildren())) {
          SContainmentLink role = child.getContainmentLink();
          fullRoot.removeChild(child);
          root.addChild(role, child);
        }
      } else if (root instanceof InterfaceSNode) {
        update((InterfaceSNode) root);
      }
    }
  }

  private void update(InterfaceSNode node) {
    if (node.hasSkippedChildren()) {
      SNode fullNode = myFullModel.getNode(node.getNodeId());
      if (fullNode == null) {
        final String m = "model %s: no peer node in full model for %s (in %s)";
        LOG.error(String.format(m, myModel.getModelName(), node.getNodeId(), myDataOwner.getSource().getLocation()));
        return;
      }
      Iterator<? extends SNode> it = fullNode.getChildren().iterator();
      SNode curr = it.hasNext() ? it.next() : null;

      for (SNode child : node.getChildren()) {
        SNodeId childId = child.getNodeId();
        while (curr != null && !childId.equals(curr.getNodeId())) {
          SNode next = it.hasNext() ? it.next() : null;
          SContainmentLink role = curr.getContainmentLink();
          curr.delete();
          node.insertChildBefore(role, curr, child);
          curr = next;
        }
        if (curr != null && childId.equals(curr.getNodeId())) {
          // skip
          curr = it.hasNext() ? it.next() : null;
        }
        if (curr == null) {
          break;
        }
      }
      while (curr != null) {
        SNode next = it.hasNext() ? it.next() : null;
        SContainmentLink role = curr.getContainmentLink();
        curr.delete();
        node.addChild(role, curr);
        curr = next;
      }
      node.cleanSkippedRoles();
    }
    for (SNode n : node.getChildren()) {
      if (n instanceof InterfaceSNode) {
        update((InterfaceSNode) n);
      }
    }
  }
}
