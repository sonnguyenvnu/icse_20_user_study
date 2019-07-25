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
package jetbrains.mps.nodeEditor.selection;

import jetbrains.mps.editor.runtime.cells.ReadOnlyUtil;
import jetbrains.mps.nodeEditor.cells.CellFinderUtil;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Property;
import jetbrains.mps.nodeEditor.cells.GeometryUtil;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.CellInfo;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCell_Label;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionStoreException;
import jetbrains.mps.smodel.ModelAccessHelper;
import jetbrains.mps.util.Computable;
import jetbrains.mps.util.SNodeOperations;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EditorCellLabelSelection extends EditorCellSelection {
  private static final String HAS_NON_TRIVIAL_SELECTION_PROPERTY_NAME = "hasNonTrivialSelection";
  private static final String SELECTION_START_PROPERTY_NAME = "selectionStart";
  private static final String SELECTION_END_PROPERTY_NAME = "selectionEnd";

  private int mySelectionStart = -1;
  private int mySelectionEnd = -1;
  private boolean myNonTrivialSelection = false;

  public EditorCellLabelSelection(EditorComponent editorComponent, Map<String, String> properties, CellInfo cellInfo) throws SelectionStoreException,
                                                                                                                             SelectionRestoreException {
    super(editorComponent, properties, cellInfo);
    if (!(getEditorCell() instanceof EditorCell_Label)) {
      throw new SelectionRestoreException();
    }
    myNonTrivialSelection = SelectionInfoImpl.Util.getBooleanProperty(properties, HAS_NON_TRIVIAL_SELECTION_PROPERTY_NAME);
    if (getEditorCell().getCellInfo().equals(cellInfo)) {
      if (myNonTrivialSelection) {
        /*
         This is kind of hack for EditorManager.STHintCellInfo - if located cell is different from the original one
         then we do not restore selection.
         */
        mySelectionStart = SelectionInfoImpl.Util.getIntProperty(properties, SELECTION_START_PROPERTY_NAME);
        mySelectionEnd = SelectionInfoImpl.Util.getIntProperty(properties, SELECTION_END_PROPERTY_NAME);
      }
    } else {
      myNonTrivialSelection = false;
    }
  }

  public EditorCellLabelSelection(EditorCell_Label editorCell) {
    super(editorCell);
    mySelectionStart = editorCell.getSelectionStart();
    mySelectionEnd = editorCell.getSelectionEnd();
    myNonTrivialSelection = mySelectionStart != mySelectionEnd;
  }

  @NotNull
  public EditorCell_Label getEditorCellLabel() {
    return (EditorCell_Label) getEditorCell();
  }

  public int getSelectionStart() {
    return isActive() ? getEditorCellLabel().getSelectionStart() : mySelectionStart;
  }

  public int getSelectionEnd() {
    return isActive() ? getEditorCellLabel().getSelectionEnd() : mySelectionEnd;
  }

  public boolean hasNonTrivialSelection() {
    return isActive() ? getSelectionStart() != getSelectionEnd() : myNonTrivialSelection;
  }

  @Override
  public void activate() {
    super.activate();
    if (myNonTrivialSelection) {
      getEditorCellLabel().setSelectionStart(mySelectionStart);
      getEditorCellLabel().setSelectionEnd(mySelectionEnd);
    }
  }

  @Override
  public void deactivate() {
    super.deactivate();
    mySelectionStart = getEditorCellLabel().getSelectionStart();
    mySelectionEnd = getEditorCellLabel().getSelectionEnd();
    myNonTrivialSelection = mySelectionStart != mySelectionEnd;
    getEditorCellLabel().deselectAll();
  }

  @Override
  public SelectionInfoImpl getSelectionInfo() throws SelectionStoreException {
    SelectionInfoImpl selectionInfo = super.getSelectionInfo();
    selectionInfo.getPropertiesMap().put(HAS_NON_TRIVIAL_SELECTION_PROPERTY_NAME, Boolean.toString(hasNonTrivialSelection()));
    if (hasNonTrivialSelection()) {
      selectionInfo.getPropertiesMap().put(SELECTION_START_PROPERTY_NAME, Integer.toString(getSelectionStart()));
      selectionInfo.getPropertiesMap().put(SELECTION_END_PROPERTY_NAME, Integer.toString(getSelectionEnd()));
    }
    return selectionInfo;
  }

  @Override
  public boolean isSame(Selection another) {
    if (this == another) {
      return true;
    }
    if (another == null || getClass() != another.getClass()) {
      return false;
    }

    EditorCellLabelSelection that = (EditorCellLabelSelection) another;
    if (!getEditorCell().equals(that.getEditorCell())) {
      return false;
    }
    if (getCaretX() != that.getCaretX()) {
      return false;
    }
    if (hasNonTrivialSelection() != that.hasNonTrivialSelection()) {
      return false;
    }
    if (hasNonTrivialSelection()) {
      if (getSelectionEnd() != that.getSelectionEnd()) {
        return false;
      }
      return getSelectionStart() == that.getSelectionStart();
    }
    return true;
  }

  @Override
  public boolean canExecuteAction(CellActionType type) {
    if (type == CellActionType.DELETE || type == CellActionType.BACKSPACE) {
      return true;
    }
    if (type == CellActionType.DELETE_TO_WORD_END) {
      type = CellActionType.DELETE;
    }
    return super.canExecuteAction(type);
  }

  @Override
  public void executeAction(CellActionType type) {
    ((jetbrains.mps.nodeEditor.EditorComponent) getEditorComponent()).assertModelNotDisposed();
    if (type == CellActionType.DELETE || type == CellActionType.BACKSPACE) {
      performDeleteAction(type);
      return;
    }
    if (type == CellActionType.DELETE_TO_WORD_END) {
      super.executeAction(CellActionType.DELETE);
      return;
    }
    super.executeAction(type);
  }

  @Override
  protected boolean suppressDelete(CellActionType type) {
    if (!super.suppressDelete(type)) {
      return false;
    }
    EditorCell_Label label = getEditorCellLabel();
    if (label.getText().length() == 0) {
      return false;
    }
    if (label instanceof EditorCell_Constant || label instanceof EditorCell_Property) {
      return label.isEditable() || CellFinderUtil.findLastSelectableLeaf(CellTraversalUtil.getContainingBigCell(label)) != label;
    }
    return true;
  }

  private void performDeleteAction(CellActionType type) {
    if (getEditorCellLabel().executeTextAction(type, false)) {
      return;
    }
    if (processSideDeletes(type)) {
      return;
    }
    if (getEditorCellLabel().executeTextAction(type, true)) {
      return;
    }
    super.executeAction(type);
  }

  private boolean processSideDeletes(CellActionType type) {
    // TODO: review this logic - it was originally copied from EditorComponentKeyboardHandler
    final EditorCell selectedCell = getEditorCell();
    if (type == CellActionType.DELETE && !hasNonTrivialSelection() && GeometryUtil.isLastPositionInBigCell(selectedCell) &&
        !GeometryUtil.isFirstPositionInBigCell(selectedCell)) {
      final EditorCell target;
      EditorCell bigCellNextSibling = CellTraversalUtil.getNextSibling(CellTraversalUtil.getContainingBigCell(selectedCell));
      if (bigCellNextSibling != null) {
        target = bigCellNextSibling;
      } else {
        EditorCell nextSibling = CellTraversalUtil.getNextSibling(CellTraversalUtil.getContainingBigCell(selectedCell));
        if (nextSibling != null) {
          target = nextSibling;
        } else {
          target = CellTraversalUtil.getNextLeaf(selectedCell, jetbrains.mps.openapi.editor.cells.CellConditions.SELECTABLE);
        }
      }

      Computable<Boolean> isAncestor = () -> SNodeOperations.isAncestor(target.getSNode(), selectedCell.getSNode());
      if (target == null || new ModelAccessHelper(selectedCell.getContext().getRepository()).runReadAction(isAncestor)) {
        return false;
      }

      return getEditorComponent().getActionHandler().executeAction(target, type);
    }

    if (type == CellActionType.BACKSPACE && !hasNonTrivialSelection() && GeometryUtil.isFirstPositionInBigCell(selectedCell) &&
        !GeometryUtil.isLastPositionInBigCell(selectedCell)) {
      final EditorCell target;
      EditorCell bigCellPrevSibling = CellTraversalUtil.getPrevSibling(CellTraversalUtil.getContainingBigCell(selectedCell));
      if (bigCellPrevSibling != null) {
        target = bigCellPrevSibling;
      } else {
        EditorCell prevSibling = CellTraversalUtil.getPrevSibling(selectedCell);
        if (prevSibling != null) {
          target = prevSibling;
        } else {
          target = CellTraversalUtil.getPrevLeaf(selectedCell, jetbrains.mps.openapi.editor.cells.CellConditions.SELECTABLE);
        }
      }

      if (target == null || ReadOnlyUtil.isCellReadOnly(target)) {
        return false;
      }
      /*
        Was commented out (again) to let some of our unit-tests be green.
        in particular - pressing BackSpace at this situation:
          <code>
            int a = 1;
            --|a;
          <code>
        where "|" is a position of cursor;
      if (ModelAccess.instance().runReadAction(new Computable<Boolean>() {
        public Boolean compute() {
          return jetbrains.mps.util.SNodeOperations.isAncestor(target.getSNode(), selectedCell.getSNode());
        }
      })) return false;
        */
      return getEditorComponent().getActionHandler().executeAction(target, type);
    }
    return false;
  }

  public boolean isExactlyCoveringCell(EditorCell cell) {
    if (getSelectionStart() != 0 || getSelectionEnd() != getEditorCellLabel().getText().length()) {
      return false;
    }
    return super.isExactlyCoveringCell(cell);
  }


  @Override
  public String toString() {
    return String.format("EditorCellLabelSelection{cell=%s, start=%d, end=%d}", getEditorCell(), mySelectionStart, mySelectionEnd);
  }
}
