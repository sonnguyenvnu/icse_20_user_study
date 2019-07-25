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
package jetbrains.mps.nodeEditor.braces;

import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.nodeEditor.braces.BracesFinder.Algorithm;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

class MatchingLabelBracesFinder implements Algorithm {
  @Nullable
  @Override
  public BracePair find(@NotNull EditorCell selectedCell) {
    final Pair<EditorCell, String> matchingLabelAndCell = getMatchingLabelAndCell(selectedCell);
    if (matchingLabelAndCell == null) {
      return null;
    }

    final EditorCell matchingCell = matchingLabelAndCell.o1;
    EditorCell bigCell = CellTraversalUtil.getContainingBigCell(selectedCell);

    EditorCell editorCell = CellFinderUtil.findChildByCondition(bigCell, cell -> isMatchingLabelAndCell(matchingLabelAndCell, cell), true);
    return editorCell != null ? new BracePair(editorCell, matchingCell) : null;
  }

  private Pair<EditorCell, String> getMatchingLabelAndCell(EditorCell editorCell) {
    SNode node = editorCell.getSNode();
    while (editorCell != null && editorCell.getSNode() == node) {
      if (editorCell.getStyle().get(StyleAttributes.MATCHING_LABEL) != null) {
        return new Pair<>(editorCell, editorCell.getStyle().get(StyleAttributes.MATCHING_LABEL));
      }
      editorCell = editorCell.getParent();
    }
    return null;
  }

  private boolean isMatchingLabelAndCell(Pair<EditorCell, String> matchingLabelAndCell, EditorCell cell) {
    EditorCell matchingCell = matchingLabelAndCell.o1;
    return cell != matchingCell
        && cell.getSNode() == matchingCell.getSNode()
        && matchingLabelAndCell.o2.equals(cell.getStyle().get(StyleAttributes.MATCHING_LABEL));
  }
}
