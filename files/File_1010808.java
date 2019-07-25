/*
 * Copyright 2003-2014 JetBrains s.r.o.
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
package jetbrains.mps.nodeEditor.sidetransform;

import com.intellij.ui.LightColors;
import jetbrains.mps.editor.runtime.SideTransformInfoUtil;
import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.editor.runtime.cells.KeyMapActionImpl;
import jetbrains.mps.editor.runtime.cells.KeyMapImpl;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.nodeEditor.cellActions.SideTransformSubstituteInfo;
import jetbrains.mps.nodeEditor.cellActions.SideTransformSubstituteInfo.Side;
import jetbrains.mps.nodeEditor.cells.DefaultCellInfo;
import jetbrains.mps.nodeEditor.cells.EditorCell_Constant;
import jetbrains.mps.nodeEditor.cells.EditorCell_Label;
import jetbrains.mps.nodeEditor.cells.SynchronizeableEditorCell;
import jetbrains.mps.openapi.editor.EditorComponent;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.CellInfo;
import jetbrains.mps.openapi.editor.cells.EditorCell;
import jetbrains.mps.openapi.editor.cells.EditorCellContext;
import jetbrains.mps.openapi.editor.cells.EditorCell_Collection;
import jetbrains.mps.openapi.editor.cells.KeyMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;

/**
 * User: shatalin
 * Date: 27/02/14
 */
public class EditorCell_STHint extends EditorCell_Constant {
  private static final String CELL_ID = "STHint";

  @Nullable
  private final CellInfo myRestoreSelectionCellInfo;
  @NotNull
  private final EditorCell myBigCell;
  @NotNull
  private final EditorCell myAnchorCell;
  @NotNull
  private final Side mySide;
  private boolean myInstalled;

  public static EditorCell_STHint getSTHintCell(SNode node, @NotNull EditorComponent editorComponent) {
    EditorCell stHintCell = editorComponent.findCellWithId(node, CELL_ID);
    return stHintCell instanceof EditorCell_STHint ? (EditorCell_STHint) stHintCell : null;
  }

  public EditorCell_STHint(@NotNull EditorCell bigCell, @NotNull EditorCell anchorCell, @NotNull Side side, @Nullable CellInfo restoreSelectionCellInto) {
    super(anchorCell.getContext(), anchorCell.getSNode(), "");
    assert bigCell.isBig();
    myBigCell = bigCell;
    myAnchorCell = anchorCell;
    mySide = side;
    myRestoreSelectionCellInfo = restoreSelectionCellInto;
    init();
  }

  private void init() {
    setCellId(CELL_ID);
    setDefaultText(" ");
    setEditable(true);
    getStyle().set(StyleAttributes.BACKGROUND_COLOR, LightColors.BLUE);

    getStyle().set(StyleAttributes.PUNCTUATION_LEFT, true);
    getStyle().set(StyleAttributes.PUNCTUATION_RIGHT, true);
    getStyle().set(StyleAttributes.FIRST_POSITION_ALLOWED, true);
    getStyle().set(StyleAttributes.LAST_POSITION_ALLOWED, true);

    RemoveSTHintAction removeSTHintAction = new RemoveSTHintAction();
    // delete the hint when pressed ctrl-delete, delete or backspace
    setAction(CellActionType.DELETE, removeSTHintAction);
    setAction(CellActionType.BACKSPACE, removeSTHintAction);
    // delete the hint when double press 'space'
    setAction(CellActionType.RIGHT_TRANSFORM, removeSTHintAction);
    setAction(CellActionType.LEFT_TRANSFORM, removeSTHintAction);

    // delete the hint when double press 'esc'
    KeyMap keyMap = new KeyMapImpl();
    keyMap.putAction(KeyMap.KEY_MODIFIERS_NONE, "VK_ESCAPE", new RemoveSTHintKeyMapAction());
    addKeyMap(keyMap);

    // create the hint's auto-completion menu
    setSubstituteInfo(new SideTransformSubstituteInfo(myAnchorCell, mySide));
  }


  @NotNull
  @Override
  public CellInfo getCellInfo() {
    return new STHintCellInfo(EditorCell_STHint.this, myAnchorCell);
  }

  @Override
  public void changeText(String text) {
    super.changeText(text);
    if (getText() != null && getText().isEmpty()) {
      SideTransformInfoUtil.removeTransformInfo(getSNode());
    }
  }

  @Override
  public void setCaretPosition(int position, boolean selection) {
    if (position != getText().length() && !isRight()) {
      validate(true, false);
    }
    super.setCaretPosition(position, selection);
  }

  @Override
  public void synchronizeViewWithModel() {
  }

  private void removeSTHintAndChangeSelection(final EditorContext context) {
    SideTransformInfoUtil.removeTransformInfo(getSNode());
    context.flushEvents();

    if (myRestoreSelectionCellInfo == null) {
      return;
    }

    jetbrains.mps.nodeEditor.EditorComponent editorComponent = (jetbrains.mps.nodeEditor.EditorComponent) context.getEditorComponent();
    EditorCell newlySelectedCell = myRestoreSelectionCellInfo.findCell(editorComponent);
    if (newlySelectedCell == null) {
      return;
    }
    editorComponent.changeSelection(newlySelectedCell);
    if (newlySelectedCell instanceof EditorCell_Label) {
      newlySelectedCell.end();
    }
  }

  public EditorCell install() {
    assert !myInstalled;
    myInstalled = true;
    if (myAnchorCell == myBigCell) {
      // Creating wrapper collection to hold both nodeCell & STHintCell
      jetbrains.mps.nodeEditor.cells.EditorCell_Collection wrapperCell =
          jetbrains.mps.nodeEditor.cells.EditorCell_Collection.createHorizontal(getContext(), getSNode());
      wrapperCell.setSelectable(false);
      wrapperCell.setBig(true);
      EditorCellContext cellContext = myBigCell.getCellContext();
      assert cellContext != null;
      wrapperCell.setCellContext(cellContext);
      wrapperCell.setCanBeSynchronized(myBigCell instanceof SynchronizeableEditorCell && ((SynchronizeableEditorCell) myBigCell).canBeSynchronized());
      myBigCell.setBig(false);
      if (!isRight()) {
        wrapperCell.addEditorCell(this);
      }
      wrapperCell.addEditorCell(myBigCell);
      if (isRight()) {
        wrapperCell.addEditorCell(this);
      }
      return wrapperCell;
    }

    EditorCell_Collection cellCollection = myAnchorCell.getParent();
    if (isRight()) {
      cellCollection.addEditorCellAfter(this, myAnchorCell);
    } else {
      cellCollection.addEditorCellBefore(this, myAnchorCell);
    }
    return myBigCell;
  }

  private boolean isRight() {
    return mySide == Side.RIGHT;
  }

  @NotNull
  public Side getSide() {
    return mySide;
  }

  public EditorCell uninstall() {
    assert myInstalled;
    if (myAnchorCell == myBigCell) {
      // not necessary to remove EditorCell_STHint from wrapper
      // collection - just returning myBigCell in the end
      myBigCell.setBig(true);
    } else {
      this.getParent().removeCell(this);
    }
    return myBigCell;
  }

  private class RemoveSTHintAction extends AbstractCellAction {
    @Override
    public void execute(EditorContext context) {
      removeSTHintAndChangeSelection(context);
    }
  }

  private class RemoveSTHintKeyMapAction extends KeyMapActionImpl {
    @Override
    public void execute(EditorContext context) {
      removeSTHintAndChangeSelection(context);
    }
  }

  private static class STHintCellInfo extends DefaultCellInfo {
    CellInfo myAnchorCellInfo;

    STHintCellInfo(EditorCell_STHint rightTransformHintCell, EditorCell anchorCell) {
      super(rightTransformHintCell);
      myAnchorCellInfo = anchorCell.getCellInfo();
    }

    @Override
    public EditorCell findCell(@NotNull EditorComponent editorComponent) {
      EditorCell anchorCell = myAnchorCellInfo.findCell(editorComponent);
      return anchorCell != null ? getSTHintCell(anchorCell.getSNode(), editorComponent) : super.findCell(editorComponent);
    }

    @Override
    public EditorCell findClosestCell(@NotNull EditorComponent editorComponent) {
      EditorCell anchorCell = myAnchorCellInfo.findCell(editorComponent);
      if (anchorCell == null) {
        return super.findCell(editorComponent);
      }
      EditorCell_Label rtHint = getSTHintCell(anchorCell.getSNode(), editorComponent);
      return rtHint != null ? rtHint : (jetbrains.mps.nodeEditor.cells.EditorCell) anchorCell;
    }
  }
}
