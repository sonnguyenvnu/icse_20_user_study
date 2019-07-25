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
package jetbrains.mps.nodeEditor.folding;

import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import org.jetbrains.mps.util.Condition;

public class CellAction_FoldCell extends AbstractCellAction {
  public CellAction_FoldCell() {
    super(false);
  }

  @Override
  public boolean canExecute(EditorContext context) {
    EditorCell editorCell = context.getSelectedCell();
    if (editorCell == null) return false;
    return findCell(editorCell) != null;
  }

  @Override
  public void execute(EditorContext context) {
    EditorCell editorCell = context.getSelectedCell();
    EditorCell_Collection targetCell = findCell(editorCell);
    targetCell.fold();
  }

  private static EditorCell_Collection findCell(EditorCell editorCell) {
    return CellFinderUtil.findParent(editorCell, object -> object.isFoldable() && !object.isCollapsed());
  }
}
