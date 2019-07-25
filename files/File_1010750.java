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
package jetbrains.mps.nodeEditor.checking;

import jetbrains.mps.editor.runtime.commands.EditorCommand;
import jetbrains.mps.editor.runtime.style.StyleAttributesUtil;
import jetbrains.mps.errors.item.QuickFixBase;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;

public class QuickFixRuntimeEditorWrapper {

  private final QuickFixBase myQuickFix;

  private QuickFixRuntimeEditorWrapper(QuickFixBase quickFix) {
    myQuickFix = quickFix;
  }

  public static QuickFixRuntimeEditorWrapper getInstance(QuickFixBase quickFix) {
    return new QuickFixRuntimeEditorWrapper(quickFix);
  }

  public void execute(EditorContext editorContext, boolean saveSelection) {
    if (saveSelection) {
      EditorCell selectedCell = editorContext.getSelectedCell();
      if (selectedCell == null) {
        return;
      }
      int caretX = selectedCell.getCaretX();
      int caretY = selectedCell.getBaseline();

      execute(editorContext);

      editorContext.flushEvents();
      EditorCell rootCell = editorContext.getEditorComponent().getRootCell();
      EditorCell leaf = rootCell.findLeaf(caretX, caretY);

      // Currently findLeaf() doesn't look at firstPositionAllowed and uses strict inequality for last position and loose one for first.
      // Since we need to put cursor at the most close position to the previous one,  so checking it manually.
      if (leaf != null && leaf.getX() == caretX && !StyleAttributesUtil.isFirstPositionAllowed(leaf.getStyle())) {
        while (leaf != null && leaf.getPrevSibling() == null) {
          leaf = leaf.getParent();
        }
        if (leaf != null) {
          leaf = CellFinderUtil.findLastSelectableLeaf(leaf.getPrevSibling(), true);
        }
      }

      if (leaf != null) {
        editorContext.getEditorComponent().changeSelection(leaf);
        leaf.setCaretX(caretX);
      }
    } else {
      execute(editorContext);
    }
  }

  private void execute(EditorContext editorContext) {
    if (myQuickFix instanceof EditorContextQuickfix) {
      ((EditorContextQuickfix) myQuickFix).execute(editorContext);
    } else {
      myQuickFix.execute(editorContext.getRepository());
    }
  }
}
