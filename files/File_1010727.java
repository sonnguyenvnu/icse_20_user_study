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

import jetbrains.mps.editor.runtime.SideTransformInfoUtil;
import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.nodeEditor.CellSide;
import jetbrains.mps.nodeEditor.cellActions.SideTransformSubstituteInfo.Side;
import jetbrains.mps.nodeEditor.cells.EditorCell_Error;
import jetbrains.mps.nodeEditor.sidetransform.EditorCell_STHint;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.SubstituteInfo;
import jetbrains.mps.util.annotation.ToRemove;
import org.jetbrains.mps.openapi.model.SNode;


public class CellAction_SideTransform extends AbstractCellAction {
  private Side mySide;

  /**
   * use {@link #CellAction_SideTransform(Side)}
   */
  @Deprecated
  @ToRemove(version = 2017.2)
  public CellAction_SideTransform(CellSide side) {
    mySide = side == CellSide.RIGHT ? Side.RIGHT : Side.LEFT;
  }

  public CellAction_SideTransform(Side side) {
    mySide = side;
  }

  @Override
  public boolean canExecute(EditorContext context) {
    return context.getSelectedCell() != null && canCreateRightTransformHint(context.getSelectedCell());
  }

  private boolean canCreateRightTransformHint(EditorCell selectedCell) {
    SNode node = selectedCell.getSNode();
    if (node == null) {
      return false;
    }
    if (selectedCell instanceof EditorCell_Error) {
      return false;
    }

    return !getSubstituteInfo(selectedCell).getMatchingActions("", false).isEmpty();
  }

  private SubstituteInfo getSubstituteInfo(EditorCell anchorCell) {
    return new SideTransformSubstituteInfo(anchorCell, mySide);
  }

  @Override
  public void execute(EditorContext context) {
    EditorCell selectedCell = context.getSelectedCell();
    SNode node = selectedCell.getSNode();

    SideTransformInfoUtil.removeTransformInfo(node);

    String anchorCellId = selectedCell.getCellId();

    if (mySide == Side.LEFT) {
      SideTransformInfoUtil.addLeftTransformInfo(node, anchorCellId);
    } else {
      SideTransformInfoUtil.addRightTransformInfo(node, anchorCellId);
    }
    context.flushEvents();

    EditorCell_STHint sideTransformHintCell = EditorCell_STHint.getSTHintCell(node, context.getEditorComponent());
    assert
        sideTransformHintCell != null :
        "STHint cell was not created. Node: " + node + " (concept: " + node.getConcept().getQualifiedName() + " )" + ", anchorCellID: " + anchorCellId;
    sideTransformHintCell.setSubstituteInfo(getSubstituteInfo(selectedCell));
    context.getEditorComponent().changeSelection(sideTransformHintCell);
  }
}
