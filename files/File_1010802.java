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
package jetbrains.mps.nodeEditor.selection;

import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionManager;

import java.util.function.BooleanSupplier;

public class SelectUpUtil {
  public static boolean canExecute(EditorContext context) {
    return findTarget(context.getEditorComponent().getSelectionManager()) != null;
  }

  public static void execute(EditorContext context) {
    SelectionManager selectionManager = context.getEditorComponent().getSelectionManager();
    EditorCell cell = findTarget(selectionManager);
    selectionManager.pushSelection(selectionManager.createSelection(cell));
    if (cell instanceof EditorCell_Label) {
      ((EditorCell_Label) cell).selectWordOrAll();
    }
  }

  public static void executeWhile(EditorContext context, BooleanSupplier condition) {
    while (canExecute(context) && condition.getAsBoolean()) {
      execute(context);
    }
  }

  private static EditorCell findTarget(SelectionManager selectionManager) {
    Selection selection = selectionManager.getSelection();
    if (selection == null) {
      return null;
    }

    EditorCell cell = selection.getSelectedCells().get(0);
    if (cell instanceof EditorCell_Label && !((EditorCell_Label) cell).isEverythingSelected()) {
      return cell;
    }

    if (cell.getParent() == null) {
      return null;
    }

    while (cell.getParent() != null && cell.getParent().isTransparentCollection()) {
      cell = cell.getParent();
    }
    EditorCell_Collection parent = cell.getParent();
    while (parent != null) {
      if (parent.isSelectable()) {
        while (parent.getParent() != null && parent.getParent().isTransparentCollection() && parent.getParent().isSelectable()) {
          parent = parent.getParent();
        }
        return parent;
      }
      parent = parent.getParent();
    }
    return null;
  }
}
