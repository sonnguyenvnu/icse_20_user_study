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
package jetbrains.mps.nodeEditor.cellActions;

import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.nodeEditor.cellProviders.AbstractCellListHandler;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;

public class CellAction_InsertIntoCollection extends AbstractCellAction {
  private AbstractCellListHandler myListHandler;
  private boolean myInsertBefore;

  public CellAction_InsertIntoCollection(AbstractCellListHandler listHandler, boolean insertBefore) {
   this.myListHandler = listHandler;
    myInsertBefore = insertBefore;
  }

  @Override
  public boolean canExecute(EditorContext context) {
    return true;
  }

  @Override
  public void execute(EditorContext context) {
    boolean before = myInsertBefore;
    EditorCell contextCell = context.getSelectedCell();
    myListHandler.insertNewChild(context, contextCell, before);
  }
}
