/*
 * Copyright 2003-2016 JetBrains s.r.o.
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

import jetbrains.mps.RuntimeFlags;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.mps.openapi.language.SContainmentLink;
import org.jetbrains.mps.openapi.language.SProperty;
import org.jetbrains.mps.openapi.model.SModel;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.model.SNodeUtil;
import org.jetbrains.mps.openapi.model.SReference;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CopyUtil {
  private CopyUtil() {
  }

  public static void copyModelContent(SModel from, SModel to) {
    for (SNode root : from.getRootNodes()) {
      to.addRootNode(copy(root));
    }
  }

  public static void copyModelContentAndUpdateCrossRootReferences(SModel from, SModel to) {
    // copy content and update references for targets in the same model to point to copied counterparts
    // we gonna record each cloned node, map would be huge
    HashMap<SNode, SNode> nodeMap = new HashMap<>(1 << 10);
    ArrayList<SNode> newRoots = new ArrayList<>();
    ArrayDeque<SNode> queue = new ArrayDeque<>();
    for (SNode r : from.getRootNodes()) {
      queue.addLast(r);
      while (!(queue.isEmpty())) {
        SNode n = queue.removeFirst();
        SNode copy = to.createNode(n.getConcept());
        nodeMap.put(n, copy);
        copyProperties(n, copy);
        copyUserObjects(n, copy);
        SContainmentLink ownerLink = n.getContainmentLink();
        if (ownerLink == null) {
          newRoots.add(copy);
        } else {
          // add a copy as a child into our parent's copy.
          // We walk source model from top to bottom, no chance to lack mapping for parent node
          nodeMap.get(n.getParent()).addChild(ownerLink, copy);
        }
        for (SNode ch : n.getChildren()) {
          queue.addLast(ch);
        }
      }
    }
    // Once map is ready, we can update references, so that references between original nodes are restored to their copies
    // despite lack of elements from newRoots, implicitly updates nodeMap.get(r) counterparts, i.e. new nodes
    for (SNode n : SNodeUtil.getDescendants(from.getRootNodes())) {
      // XXX would be great to re-use code of CloneUtil from generator
      // This code is similar to CopyUtil.addReferences without odd arguments, static checks and dead branches.
      SNode copy = nodeMap.get(n);
      if (copy == null) {
        continue;
      }
      for (SReference ref : n.getReferences()) {
        SNode targetNode = SNodeOperations.getTargetNodeSilently(ref);
        if (targetNode != null) {
          SNode newTarget = (nodeMap.containsKey(targetNode) ? nodeMap.get(targetNode) : targetNode);
          copy.setReference(ref.getLink(), jetbrains.mps.smodel.SReference.create(ref.getLink(), copy, newTarget));
        } else {
          String resolveInfo = (ref instanceof jetbrains.mps.smodel.SReference ? ((jetbrains.mps.smodel.SReference) ref).getResolveInfo() : null);
          copy.setReference(ref.getLink(), jetbrains.mps.smodel.SReference.create(ref.getLink(), copy, ref.getTargetNodeReference(), resolveInfo));
        }
      }
    }
    for (SNode r : newRoots) {
      to.addRootNode(r);
    }
  }

  public static void copyModelContentAndPreserveIds(SModel from, SModel to) {
    for (SNode root : from.getRootNodes()) {
      to.addRootNode(copyAndPreserveId(root, true));
    }
  }

  private static void copyModelContentAndPreserveIds(jetbrains.mps.smodel.SModel from, jetbrains.mps.smodel.SModel to) {
    for (SNode root : from.getRootNodes()) {
      to.addRootNode(copyAndPreserveId(root, true));
    }
  }

  public static void copyModelProperties(jetbrains.mps.smodel.SModel from, jetbrains.mps.smodel.SModel to) {
    from.copyPropertiesTo(to);
  }

  public static jetbrains.mps.smodel.SModel copyModel(jetbrains.mps.smodel.SModel model) {
    jetbrains.mps.smodel.SModel copy = model.createEmptyCopy();
    copyModelContentAndPreserveIds(model, copy);
    copyModelProperties(model, copy);
    return copy;
  }

  public static List<SNode> copy(List<SNode> nodes) {
    return copy(nodes, new HashMap<>());
  }

  public static List<SNode> copy(List<SNode> nodes, Map<SNode, SNode> mapping) {
    List<SNode> result = clone(nodes, mapping);
    for (SNode node : nodes) {
      addReferences(node, mapping, false);
    }
    return result;
  }

  public static SNode copy(SNode node) {
    return copy(node, new HashMap<>(), true);
  }

  public static SNode copyAndPreserveId(SNode node) {
    return copyAndPreserveId(node, true);
  }

  public static SNode copyAndPreserveId(SNode node, boolean cloneRefs) {
    HashMap<SNode, SNode> mapping = new HashMap<>();
    SNode result = clone(node, mapping, true);
    for (SNode sourceNode : mapping.keySet()) {
      ((jetbrains.mps.smodel.SNode) mapping.get(sourceNode)).setId(sourceNode.getNodeId());
    }
    addReferences(node, mapping, cloneRefs);
    return result;
  }

  public static SNode copy(SNode node, boolean copyAttributes) {
    return copy(node, new HashMap<>(), copyAttributes);
  }

  public static SNode copy(SNode node, Map<SNode, SNode> mapping, boolean copyAttributes) {
    SNode result = clone(node, mapping, copyAttributes);
    addReferences(node, mapping, false);
    return result;
  }

  public static List<SNode> copyAndPreserveId(List<SNode> nodes, Map<SNode, SNode> mapping) {
    List<SNode> result = clone(nodes, mapping);
    for (SNode sourceNode : mapping.keySet()) {
      ((jetbrains.mps.smodel.SNode) mapping.get(sourceNode)).setId(sourceNode.getNodeId());
    }
    for (SNode node : nodes) {
      addReferences(node, mapping, false);
    }
    return result;
  }

  private static SNode clone(SNode node, Map<SNode, SNode> mapping, boolean copyAttributes) {
    if (node == null) return null;

    jetbrains.mps.smodel.SNode result = new jetbrains.mps.smodel.SNode(node.getConcept());
    mapping.put(node, result);
    copyProperties(node, result);
    copyUserObjects(node, result);
    for (SNode child : node.getChildren()) {
      if (!copyAttributes && AttributeOperations.isAttribute(child)) continue;
      SContainmentLink role = child.getContainmentLink();
      assert role != null;
      result.addChild(role, clone(child, mapping, copyAttributes));
    }

    return result;
  }

  private static List<SNode> clone(List<? extends SNode> nodes, Map<SNode, SNode> mapping) {
    List<SNode> results = new ArrayList<>();
    for (SNode node : nodes) {
      results.add(clone(node, mapping, true));
    }
    return results;
  }

  public static void copyProperties(SNode from, SNode to) {
    for (SProperty p : from.getProperties()) {
      to.setProperty(p, from.getProperty(p));
    }
  }

  public static void copyUserObjects(SNode from, final SNode to) {
    for (Object key : from.getUserObjectKeys()) {
      to.putUserObject(key, from.getUserObject(key));
    }
  }

  public static void addReferences(SNode root, Map<SNode, SNode> mapping, boolean forceCloneRefs) {
    if (root == null) return;
    Iterable<SNode> thisAndDesc = SNodeUtil.getDescendants(root);
    for (SNode inputNode : thisAndDesc) {
      SNode outputNode = mapping.get(inputNode);
      if (outputNode == null) continue;

      for (SReference ref : inputNode.getReferences()) {
        boolean cloneRefs = forceCloneRefs || RuntimeFlags.isMergeDriverMode();
        SNode inputTargetNode = cloneRefs ? null : jetbrains.mps.util.SNodeOperations.getTargetNodeSilently(ref);
        if (inputTargetNode == null) { //broken reference or need to clone
          if (ref instanceof StaticReference) {
            StaticReference statRef = (StaticReference) ref;
            SReference reference = new StaticReference(
                statRef.getLink(),
                outputNode,
                statRef.getTargetSModelReference(),
                statRef.getTargetNodeId(),
                statRef.getResolveInfo());
            outputNode.setReference(reference.getLink(), reference);
          } else if (ref instanceof DynamicReference && cloneRefs) {
            DynamicReference dynRef = (DynamicReference) ref;
            DynamicReference output = new DynamicReference(dynRef.getLink(), outputNode, dynRef.getTargetSModelReference(), dynRef.getResolveInfo());
            output.setOrigin(dynRef.getOrigin());
            outputNode.setReference(output.getLink(), output);
          }
        } else if (mapping.containsKey(inputTargetNode)) {
          outputNode.setReference(ref.getLink(), jetbrains.mps.smodel.SReference.create(ref.getLink(), outputNode, mapping.get(inputTargetNode)));
        } else {
          outputNode.setReference(ref.getLink(), jetbrains.mps.smodel.SReference.create(ref.getLink(), outputNode, inputTargetNode));
        }
      }
    }
  }
}
