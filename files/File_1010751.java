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
package jetbrains.mps.nodeEditor;

import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.Computable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.language.SAbstractLink;
import org.jetbrains.mps.openapi.language.SReferenceLink;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.SRepository;

import java.util.HashSet;
import java.util.Set;

public class ChildrenCollectionFinder {
  @NotNull
  private final EditorCell myCurrent;
  @NotNull
  private final EditorCell myAnchor;
  private final boolean myForward;
  private final boolean myCheckFirst;

  public ChildrenCollectionFinder(@NotNull EditorCell current, @NotNull EditorCell anchor, boolean forward, boolean checkFirst) {
    myCurrent = current;
    myAnchor = anchor;
    myForward = forward;
    myCheckFirst = checkFirst;
  }

  public ChildrenCollectionFinder(@NotNull EditorCell current, boolean forward, boolean checkFirst) {
    this(current, current, forward, checkFirst);
  }

  public EditorCell find() {
    SRepository repo = myCurrent.getContext().getRepository();
    return new ModelAccessHelper(repo).runReadAction(() -> {
      if (myCheckFirst && isMultipleCollectionCell(myCurrent)) {
        return myCurrent;
      }
      SNode anchorNode = getLCA(myAnchor.getSNode(), myCurrent.getSNode());
      if (anchorNode == null) {
        return null;
      }
      // Note: traverser doesn't visit parent nodes of the current node,
      // if our anchor is from another subtree, search parents (up to LCA) first
      // TODO remove
      if (anchorNode != myCurrent.getSNode()) {
        EditorCell curr = myCurrent;
        while (curr != null && curr.getSNode() != anchorNode) {
          if (isMultipleCollectionCell(curr)) {
            return curr;
          }
          curr = curr.getParent();
        }
        if (curr != null && curr.getSNode() == anchorNode && isMultipleCollectionCell(curr)) {
          return curr;
        }
      }

      for (EditorCell current : CellTraversalUtil.iterateTree(null, myCurrent, myForward).skipStart()) {
        SNode currentNode = current.getSNode();

        if (!jetbrains.mps.util.SNodeOperations.isAncestor(anchorNode, currentNode)) {
          return null;
        }

        if (isMultipleCollectionCell(current)) {
          return current;
        }
      }
      return null;
    });
  }

  private static boolean isMultipleCollectionCell(EditorCell current) {
    if (current.getSRole() == null) {
      return false;
    }

    SAbstractLink role = ((SAbstractLink) current.getSRole());
    return role != null && (!(role instanceof SReferenceLink)) && role.isMultiple();
  }

  private static SNode getLCA(SNode left, SNode right) {
    if (left == right || right == null || left == null) {
      return left == null ? right : left;
    }
    Set<SNode> parents = new HashSet<>();
    while (left != null) {
      parents.add(left);
      left = left.getParent();
    }
    while (right != null) {
      if (parents.contains(right)) return right;
      right = right.getParent();
    }
    return null;
  }
}
