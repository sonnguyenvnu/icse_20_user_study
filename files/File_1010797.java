/*
 * Copyright 2003-2019 JetBrains s.r.o.
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

import jetbrains.mps.editor.runtime.commands.EditorCommandAdapter;
import jetbrains.mps.nodeEditor.cells.GeometryUtil;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.CellAction;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Label;
import jetbrains.mps.openapi.editor.selection.MultipleSelection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.ModelAccess;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class AbstractMultipleSelection extends AbstractSelection implements MultipleSelection {
  private List<EditorCell> mySelectedCells;

  public AbstractMultipleSelection(@NotNull EditorComponent editorComponent) {
    super(editorComponent);
  }

  // this method should be called from the constructor of sub-classes
  protected void setSelectedCells(@NotNull List<EditorCell> selectedCells) {
    mySelectedCells = selectedCells;
    assert mySelectedCells.size() > 0;
  }

  @Override
  public void activate() {
    ((jetbrains.mps.nodeEditor.EditorComponent) getEditorComponent()).scrollRectToVisible(GeometryUtil.getBounds(getFirstCell(), getLastCell()));
  }

  @Override
  public void deactivate() {
  }


  @Override
  public boolean canExecuteAction(CellActionType type) {
    return getAction(type) != null;
  }

  @Override
  public void executeAction(CellActionType type) {
    ((jetbrains.mps.nodeEditor.EditorComponent) getEditorComponent()).assertModelNotDisposed();
    CellAction action = getAction(type);
    if (action == null) {
      return;
    }
    // next code is similar to CellActionExecutor, which is active for single selection (through ActionHandler).
    // we shall keep model read/command context the same for actions run both in single and multiple selection.
    // XXX refactor the code to share same logic (alternatively, may introduce a method in ActionHandler to invoke an action without context cell,
    //     so that both selection implementation alternatives use ActionHandler)
    class ActionExecutor implements Runnable {
      private final EditorContext myEditorContext;
      private final CellAction myAction;

      ActionExecutor(EditorContext editorContext, CellAction action) {
        myEditorContext = editorContext;
        myAction = action;
      }

      void execute() {
        final ModelAccess modelAccess = myEditorContext.getRepository().getModelAccess();
        if (myAction.executeInCommand()) {
          modelAccess.executeCommand(new EditorCommandAdapter(this, myEditorContext));
        } else {
          // editor actions often go beyond cell information and traverse nodes associated with the cell (e.g. NodeEditorActions$EnlargeSelection),
          // keep extra burden of model read here.
          if (modelAccess.canRead()) {
            run();
          } else {
            modelAccess.runReadAction(this);
          }
        }
      }

      @Override
      public void run() {
        myAction.execute(myEditorContext);
      }
    }
    new ActionExecutor(getEditorComponent().getEditorContext(), action).execute();
  }

  private CellAction getAction(CellActionType type) {
    return getEditorComponent().getComponentAction(type);
  }

  @NotNull
  @Override
  public List<EditorCell> getSelectedCells() {
    return mySelectedCells;
  }

  @NotNull
  @Override
  public List<SNode> getSelectedNodes() {
    LinkedHashSet<SNode> result = new LinkedHashSet<>();
    for (EditorCell nextCell : getSelectedCells()) {
      SNode snode = nextCell.getSNode();
      if (snode == null) continue;
      result.add(snode);
    }
    return new ArrayList<>(result);
  }

  @NotNull
  public SNode getFirstNode() {
    return getFirstCell().getSNode();
  }

  @NotNull
  public EditorCell getFirstCell() {
    return getSelectedCells().get(0);
  }

  @NotNull
  public SNode getLastNode() {
    return getLastCell().getSNode();
  }

  @NotNull
  public EditorCell getLastCell() {
    return getSelectedCells().get(getSelectedCells().size() - 1);
  }

  @Override
  public void paintSelection(Graphics2D g) {
    jetbrains.mps.nodeEditor.EditorComponent.turnOnAliasingIfPossible(g);
    for (EditorCell cell : getSelectedCells()) {
      jetbrains.mps.nodeEditor.cells.EditorCell internalCell = (jetbrains.mps.nodeEditor.cells.EditorCell) cell;
      if (!internalCell.isInClipRegion(g)) {
        continue;
      }
      boolean wasSelected = cell.isSelected();
      cell.setSelected(true);

      boolean wasCaretEnabled = false;
      if (cell instanceof EditorCell_Label && !wasSelected) {
        EditorCell_Label label = (EditorCell_Label) cell;
        wasCaretEnabled = label.isCaretEnabled();
        label.setCaretEnabled(false);
      }

      internalCell.paint(g);
      if (cell instanceof EditorCell_Label && !wasSelected) {
        EditorCell_Label label = (EditorCell_Label) cell;
        label.setCaretEnabled(wasCaretEnabled);
      }

      cell.setSelected(wasSelected);
    }
  }

  @Override
  public void ensureVisible() {
    getEditorComponent().scrollToCell(getLastCell());
  }
}
