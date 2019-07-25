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
package jetbrains.mps.nodeEditor.selection;

import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.cells.CellAction;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.CellInfo;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.selection.Selection;
import jetbrains.mps.openapi.editor.selection.SelectionStoreException;
import jetbrains.mps.openapi.editor.selection.SingularSelection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.mps.openapi.model.SNode;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EditorCellSelection extends AbstractSelection implements SingularSelection {
  private static final String CARET_X_PROPERTY_NAME = "caretX";
  private static final String CARET_X_RELATIVE_PROPERTY_NAME = "caretXRelative";
  private static final String SIDE_SELECT_DIRECTION_PROPERTY_NAME = "sideSelectDirection";

  private EditorCell myEditorCell;
  private int myCaretX;
  private int myCaretXRelative;
  private boolean myActivateUsingRelativeCaretX = true;
  private boolean myActive = false;
  private SideSelectDirection mySideSelectDirection = SideSelectDirection.NONE;

  public EditorCellSelection(EditorComponent editorComponent, Map<String, String> properties, CellInfo cellInfo) throws SelectionStoreException,
                                                                                                                        SelectionRestoreException {
    super(editorComponent);
    if (cellInfo == null) {
      throw new SelectionStoreException("Required CellInfo parameter is null");
    }
    // TODO: think about better way to restore relevant selection in case of deleted cell (EditorManager.EditorCell_STHint)
    myEditorCell = cellInfo.findClosestCell(editorComponent);
    if (myEditorCell == null) {
      throw new SelectionRestoreException();
    }
    // Using absolute coordinates to restore caret X in case different cell was found.
    myActivateUsingRelativeCaretX = cellInfo.equals(myEditorCell.getCellInfo());
    myCaretX = SelectionInfoImpl.Util.getIntProperty(properties, CARET_X_PROPERTY_NAME);
    myCaretXRelative = SelectionInfoImpl.Util.getIntProperty(properties, CARET_X_RELATIVE_PROPERTY_NAME);
    mySideSelectDirection =
        (SideSelectDirection) SelectionInfoImpl.Util.getEnumProperty(properties, SIDE_SELECT_DIRECTION_PROPERTY_NAME, SideSelectDirection.class,
                                                                     mySideSelectDirection);
  }

  public EditorCellSelection(@NotNull EditorCell editorCell) {
    super(editorCell.getEditorComponent());
    myEditorCell = editorCell;
    myCaretX = editorCell.getCaretX();
    myCaretXRelative = getRelativeCaretX(editorCell);
  }

  @NotNull
  @Override
  public EditorCell getEditorCell() {
    return myEditorCell;
  }

  @Override
  public void setSideSelectDirection(SideSelectDirection direction) {
    mySideSelectDirection = direction;
  }

  @Override
  public SideSelectDirection getSideSelectDirection() {
    return mySideSelectDirection;
  }

  public int getCaretX() {
    return isActive() ? myEditorCell.getCaretX() : myCaretX;
  }

  public int getCaretXRelative() {
    return isActive() ? getRelativeCaretX(myEditorCell) : myCaretXRelative;
  }

  @Override
  public void activate() {
    myEditorCell.setSelected(true);
    if (myActivateUsingRelativeCaretX) {
      setRelativeCaretX(myEditorCell, getCaretXRelative());
    } else {
      myEditorCell.setCaretX(getCaretX());
      myActivateUsingRelativeCaretX = true;
    }
    myActive = true;
  }

  @Override
  public void deactivate() {
    myActive = false;
    myEditorCell.setSelected(false);
    myCaretX = myEditorCell.getCaretX();
    myCaretXRelative = getRelativeCaretX(myEditorCell);
  }

  public boolean isActive() {
    return myActive;
  }

  @Override
  public SelectionInfoImpl getSelectionInfo() throws SelectionStoreException {
    SelectionInfoImpl selectionInfo = new SelectionInfoImpl(this.getClass().getName());
    selectionInfo.setCellInfo(myEditorCell.getCellInfo());
    selectionInfo.getPropertiesMap().put(CARET_X_PROPERTY_NAME, Integer.toString(getCaretX()));
    selectionInfo.getPropertiesMap().put(CARET_X_RELATIVE_PROPERTY_NAME, Integer.toString(getCaretXRelative()));
    selectionInfo.getPropertiesMap().put(SIDE_SELECT_DIRECTION_PROPERTY_NAME, mySideSelectDirection.name());
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
    EditorCellSelection that = (EditorCellSelection) another;
    if (!myEditorCell.equals(that.myEditorCell)) {
      return false;
    }
    if (mySideSelectDirection != that.mySideSelectDirection) {
      return false;
    }
    return getCaretX() == that.getCaretX();
  }

  @Override
  public boolean canExecuteAction(CellActionType type) {
    if ((type == CellActionType.DELETE || type == CellActionType.BACKSPACE) && suppressDelete(type)) {
      return false;
    }

    CellAction applicableCellAction = getEditorComponent().getActionHandler().getApplicableCellAction(myEditorCell, type);
    return applicableCellAction != null;
  }


  @Override
  public void executeAction(CellActionType type) {
    ((jetbrains.mps.nodeEditor.EditorComponent) getEditorComponent()).assertModelNotDisposed();
    if (canExecuteAction(type)) {
      getEditorComponent().getActionHandler().executeAction(myEditorCell, type);
    }
  }

  protected boolean suppressDelete(CellActionType type) {
    return !myEditorCell.isBig() && myEditorCell.getAction(type) == null;
  }

  @NotNull
  @Override
  public List<EditorCell> getSelectedCells() {
    return Collections.singletonList(getEditorCell());
  }

  @NotNull
  @Override
  public List<SNode> getSelectedNodes() {
    SNode sNode = getEditorCell().getSNode();
    return sNode == null ? Collections.EMPTY_LIST : Collections.singletonList(sNode);
  }

  @Override
  public void ensureVisible() {
    myEditorCell.getEditorComponent().scrollToCell(myEditorCell);
  }

  private static int getRelativeCaretX(EditorCell editorCell) {
    return editorCell.getCaretX() - editorCell.getLeftGap() - editorCell.getX();
  }

  private static void setRelativeCaretX(EditorCell editorCell, int relativeCaretX) {
    editorCell.setCaretX(editorCell.getX() + editorCell.getLeftGap() + relativeCaretX);
  }

  /**
   * This method returns true if current selection exactly covers a given cell
   * There are three possibilities:
   * 1) selected cell is a child of a given cell
   * 2) selected cell is a parent of a given cell
   * 3) selected cell is in a different tree than the given cell
   * Analysis:
   * Let's call the given cell "terminal cell"
   * 1) in this case, we can say that the selected cell covers the given cell if none
   * of the cells on the materialized path from selected cell to terminal cell has any siblings. In that case
   * the selected cell covers exactly the same area as terminal cell and function should return true. This is
   * a typical case with base language
   * 2) here the situation is opposite to 1) - selected cell is in fact a parent of
   * given cell. The algorithm and reasoning is again similar, just the role of selected and terminal cells
   * are reversed, as terminal cell is some (grand)child of selected cell. The reasoning is now that if
   * all cells on the materialized path from terminal cell to selected cell have no siblings, there is no
   * difference to the user between selected and terminal cells and function should return true. Otherwise
   * it should return false
   * 3) in this case selected and terminal cells are from different trees and no containment testing makes sense.
   * Function must return false here all the time
   * <p>
   * Function conceited this way should allow reasonable testing of coverage for a wide variety of different
   * languages with overridden operations, for example if instead of a selected cell one wishes to delete
   * one of its (grand)children, or just arbitrary cell from a different tree.
   * <p>
   * This function is used in two phase deletion where it plays important role in deciding if an additional
   * highlighting step is needed. This can be then used by language designer wishing to implement two phase
   * deletion in their language as a reasonable test and not spending time on writing their own handling.
   *
   * @param cell cell
   * @return true if aforementioned conditions led us to believe all corresponding cells are covered; false otherwise
   */
  public boolean isExactlyCoveringCell(EditorCell cell) {
    if (cell == null) {
      return false;
    }
    EditorCell selectedCell = getEditorCell();
    if (selectedCell.equals(cell)) {
      return true;
    }
    boolean isParent = CellTraversalUtil.isAncestor(selectedCell, cell);
    boolean isChild = CellTraversalUtil.isAncestor(cell, selectedCell);

    if (!isParent && !isChild) {
      // from another tree
      return false;
    }

    EditorCell parentCell = isParent ? selectedCell : cell;
    EditorCell childCell = isParent ? cell : selectedCell;

    // scan cells on the path from selected cell to its terminal cell; if any cell on this path contains multiple children, highlight must change
    while (childCell != null && !childCell.equals(parentCell)) {
      if (childCell.getPrevSibling() != null || childCell.getNextSibling() != null) {
        return false;
      }
      childCell = childCell.getParent();
    }
    return true;
  }
}
