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
package jetbrains.mps.editor.runtime.deletionApprover;

import jetbrains.mps.lang.smodel.generator.smodelAdapter.AttributeOperations;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.HashSet;
import java.util.Set;

public class DeletionApproverUtil {

  public static boolean approve(@Nullable EditorContext context, @Nullable SNode node) {
    return approve(context, node, null);
  }

  public static boolean approve(@Nullable EditorContext context, @Nullable SNode node, @Nullable String cellId) {
    if (context == null || node == null) {
      return false;
    }
    EditorCell nodeCell = getNodeCell(context, node, cellId);
    if (nodeCell == null) {
      return false;
    }
    if (!context.getEditorComponent().getDeletionApprover().isApprovedForDeletion(nodeCell) &&
        !context.getSelectionManager().getSelection().isExactlyCoveringCell(nodeCell)) {
      context.getEditorComponent().getDeletionApprover().approveForDeletion(nodeCell);
      return true;
    }
    return false;
  }

  public static boolean isApprovedForDeletion(@Nullable EditorContext context, @Nullable SNode node) {
    return isApprovedForDeletion(context, node, null);
  }

  public static boolean isApprovedForDeletion(@Nullable EditorContext context, @Nullable SNode node, @Nullable String cellId) {
    if (context == null || node == null) {
      return false;
    }
    EditorCell nodeCell = getNodeCell(context, node, cellId);
    return nodeCell != null && context.getEditorComponent().getDeletionApprover().isApprovedForDeletion(nodeCell);
  }

  //todo this logic is partially copied from SelectionManagerImpl, find the way to share the code
  private static EditorCell getNodeCell(@NotNull EditorContext context, @NotNull SNode node, @Nullable String cellId) {
    EditorCell nodeCell = context.getEditorComponent().findNodeCell(node);
    if (nodeCell == null) {
      return null;
    }
    if (cellId == null) {
      Set<SNode> attributes = new HashSet<>();
      AttributeOperations.getNodeAttributes(node).iterator().forEachRemaining(attributes::add);
      EditorCell parent = nodeCell.getParent();
      while (parent != null && attributes.contains(parent.getSNode())) {
        nodeCell = parent;
        parent = parent.getParent();
      }
      return nodeCell;
    }
    return findChildCell(nodeCell, cellId);
  }

  private static EditorCell findChildCell(EditorCell nodeCell, String cellId) {
    for (EditorCell cell : CellTraversalUtil.iterateTree(nodeCell, nodeCell, true)) {
      if (isSpecifiedById(cell, cellId)) {
        return cell;
      }
    }
    return null;
  }

  private static boolean isSpecifiedById(EditorCell cell, String requestedCellId) {
    String thisCellId = cell.getCellId();
    // supporting this hidden notation for selecting property cells.
    // Now property cellIDs are prefixed with editor component name.
    if (requestedCellId.startsWith("*") && thisCellId != null && thisCellId.contains(requestedCellId.substring(1))) {
      return true;
    }
    return requestedCellId.equals(thisCellId);
  }
}
