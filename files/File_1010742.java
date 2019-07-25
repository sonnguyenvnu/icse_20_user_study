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
package jetbrains.mps.nodeEditor.cells;

import com.intellij.openapi.command.CommandProcessor;
import jetbrains.mps.editor.runtime.TextBuilderImpl;
import jetbrains.mps.editor.runtime.cells.AbstractCellAction;
import jetbrains.mps.editor.runtime.cells.CaretState;
import jetbrains.mps.editor.runtime.commands.EditorComputable;
import jetbrains.mps.editor.runtime.style.Padding;
import jetbrains.mps.editor.runtime.style.StyleAttributes;
import jetbrains.mps.editor.runtime.style.StyleAttributesUtil;
import jetbrains.mps.ide.datatransfer.CopyPasteUtil;
import jetbrains.mps.ide.datatransfer.TextPasteUtil;
import jetbrains.mps.nodeEditor.CellSide;
import jetbrains.mps.nodeEditor.IntelligentInputUtil;
import jetbrains.mps.nodeEditor.IntelligentInputUtil.IntelligentCellProcessor;
import jetbrains.mps.nodeEditor.cellMenu.NodeSubstitutePatternEditor;
import jetbrains.mps.nodeEditor.keyboard.TextChangeEvent;
import jetbrains.mps.nodeEditor.selection.EditorCellLabelSelection;
import jetbrains.mps.openapi.editor.EditorContext;
import jetbrains.mps.openapi.editor.TextBuilder;
import jetbrains.mps.openapi.editor.cells.CellActionType;
import jetbrains.mps.openapi.editor.cells.CellTraversalUtil;
import jetbrains.mps.openapi.editor.cells.SubstituteInfo;
import jetbrains.mps.openapi.editor.cells.optional.WithCaret;
import jetbrains.mps.openapi.editor.selection.MultipleSelection;
import jetbrains.mps.openapi.editor.selection.SelectionManager;
import jetbrains.mps.smodel.SNodeUndoableAction;
import jetbrains.mps.smodel.UndoHelper;
import jetbrains.mps.smodel.UndoRunnable;
import jetbrains.mps.util.EqualUtil;
import jetbrains.mps.util.NameUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.mps.openapi.model.SNode;
import org.jetbrains.mps.openapi.module.ModelAccess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Optional;

public abstract class EditorCell_Label extends EditorCell_Basic implements jetbrains.mps.openapi.editor.cells.EditorCell_Label, WithCaret {
  protected boolean myNoTextSet;
  protected TextLine myTextLine;
  protected TextLine myNullTextLine;
  protected CaretState myCaretState = new CaretState();

  protected EditorCell_Label(@NotNull jetbrains.mps.openapi.editor.EditorContext editorContext, SNode node, String text) {
    super(editorContext, node);
    myTextLine = new TextLine("", getStyle(), false);
    myNullTextLine = new TextLine("", getStyle(), true);

    myTextLine.setCaretEnabled(true);
    myNullTextLine.setCaretEnabled(true);
    setText(text);

    setAction(CellActionType.LEFT, new MoveLeft(false));
    setAction(CellActionType.RIGHT, new MoveRight(false));

    setAction(CellActionType.LOCAL_HOME, new LocalHome(false));
    setAction(CellActionType.LOCAL_END, new LocalEnd(false));

    setAction(CellActionType.SELECT_RIGHT, new MoveRight(true));
    setAction(CellActionType.SELECT_LEFT, new MoveLeft(true));

    setAction(CellActionType.SELECT_HOME, new SelectHome());
    setAction(CellActionType.SELECT_END, new SelectEnd());

    setAction(CellActionType.SELECT_LOCAL_HOME, new LocalHome(true));
    setAction(CellActionType.SELECT_LOCAL_END, new LocalEnd(true));

    setAction(CellActionType.COPY, new CopyLabelText());
    setAction(CellActionType.PASTE, new PasteIntoLabelText());
    setAction(CellActionType.CUT, new CutLabelText());
    setAction(CellActionType.CLEAR_SELECTION, new ClearSelection());
  }

  public boolean canPasteText() {
    return true;
  }

  @Override
  public void setSelected(boolean selected) {
    super.setSelected(selected);
    if (!selected && !getEditor().selectionStackContains(this)) {
      myTextLine.resetSelection();
    }
    myCaretState.touch(selected);
  }

  @Override
  public String getText() {
    return myTextLine.getText();
  }

  public String getNullText() {
    return myNullTextLine.getText();
  }

  public String getRenderedText() {
    return getRenderedTextLine().getText();
  }

  public Font getFont() {
    return getRenderedTextLine().getFont();
  }

  public void setTextColor(Color color) {
    getStyle().set(StyleAttributes.TEXT_COLOR, color);
  }

  public void setNullTextColor(Color color) {
    getStyle().set(StyleAttributes.NULL_TEXT_COLOR, color);
  }

  public void setTextBackgroundColor(Color color) {
    getStyle().set(StyleAttributes.TEXT_BACKGROUND_COLOR, color);
  }

  public void setNullTextBackgroundColor(Color color) {
    getStyle().set(StyleAttributes.NULL_TEXT_BACKGROUND_COLOR, color);
  }

  public void setSelectedTextBackgroundColor(Color color) {
    getStyle().set(StyleAttributes.SELECTED_TEXT_BACKGROUND_COLOR, color);
  }

  public void setNullSelectedTextBackgroundColor(Color color) {
    getStyle().set(StyleAttributes.NULL_SELECTED_TEXT_BACKGROUND_COLOR, color);
  }

  @Override
  public int getCaretPosition() {
    return myTextLine.getCaretPosition();
  }

  @Override
  public void setCaretPosition(int position) {
    setCaretPosition(position, false);
  }

  public void setCaretPositionIfPossible(int position) {
    if (isCaretPositionAllowed(position)) {
      setCaretPosition(position, false);
    }
  }

  public void setCaretPosition(int position, boolean selection) {
    assert isCaretPositionAllowed(position) : "Position " + position + " is not allowed for EditorCell_Label: \"" + myTextLine.getText() + "\"";
    repaintCaret();
    myTextLine.setCaretPosition(position, selection);
    myCaretState.touch();
    repaintCaret();
  }

  public boolean isCaretPositionAllowed(int position) {
    if (!StyleAttributesUtil.isFirstPositionAllowed(getStyle()) && position == 0) {
      return false;
    }
    if (!StyleAttributesUtil.isLastPositionAllowed(getStyle()) && position == myTextLine.getText().length()) {
      return false;
    }
    return position >= 0 && position <= myTextLine.getText().length();
  }

  @Override
  public void home() {
    int textLength = getText().length();
    if (StyleAttributesUtil.isFirstPositionAllowed(getStyle())) {
      if (textLength > 0 || StyleAttributesUtil.isLastPositionAllowed(getStyle())) {
        setCaretPosition(0);
      }
    } else {
      if (textLength > 0 && (textLength > 1 || StyleAttributesUtil.isLastPositionAllowed(getStyle()))) {
        setCaretPosition(1);
      }
    }
  }

  @Override
  public void end() {
    int textLength = getText().length();
    if (StyleAttributesUtil.isLastPositionAllowed(getStyle())) {
      if (textLength > 0 || StyleAttributesUtil.isFirstPositionAllowed(getStyle())) {
        setCaretPosition(getText().length());
      }
    } else {
      if (textLength > 0 && (textLength > 1 || StyleAttributesUtil.isFirstPositionAllowed(getStyle()))) {
        setCaretPosition(getText().length() - 1);
      }
    }
  }

  @Override
  public boolean isSelectable() {
    if (!super.isSelectable()) {
      return false;
    }

    if (getText() == null || getText().length() == 0) {
      return StyleAttributesUtil.isFirstPositionAllowed(getStyle()) && StyleAttributesUtil.isLastPositionAllowed(getStyle());
    }

    if (getText().length() == 1) {
      return StyleAttributesUtil.isFirstPositionAllowed(getStyle()) || StyleAttributesUtil.isLastPositionAllowed(getStyle());
    }

    return true;
  }

  @Override
  public boolean isFirstCaretPosition() {
    if (!StyleAttributesUtil.isFirstPositionAllowed(getStyle())) {
      return getCaretPosition() == 1;
    } else {
      return getCaretPosition() == 0;
    }
  }

  @Override
  public boolean isLastCaretPosition() {
    if (!StyleAttributesUtil.isLastPositionAllowed(getStyle())) {
      return getCaretPosition() == getText().length() - 1;
    } else {
      return getCaretPosition() == getText().length();
    }
  }

  @Override
  public boolean isCaretEnabled() {
    return myTextLine.isCaretEnabled();
  }

  @Override
  public void setCaretEnabled(boolean enabled) {
    myTextLine.setCaretEnabled(enabled);
  }

  public void setText(String text) {
    myNoTextSet = (text == null || text.length() == 0);
    myTextLine.setText(myNoTextSet ? null : text);
    requestRelayout();
  }

  public void setDefaultText(String text) {
    myNullTextLine.setText(text);
  }

  @Override
  public int getEffectiveWidth() {
    return getTextLineWidth();
  }

  @Override
  public int getLeftInset() {
    return getRenderedTextLine().getPaddingLeft() + myGapLeft;
  }

  @Override
  public int getRightInset() {
    return getRenderedTextLine().getPaddingRight() + myGapRight;
  }

  @Override
  public int getTopInset() {
    return getRenderedTextLine().getPaddingTop();
  }

  @Override
  public int getBottomInset() {
    return getRenderedTextLine().getPaddingBottom();
  }

  public int getTextLineWidth() {
    int textLineWidth;
    if (myNoTextSet && myTextLine.getText().length() == 0) {
      textLineWidth = myNullTextLine.getEffectiveWidth();
    } else {
      textLineWidth = myTextLine.getEffectiveWidth();
    }
    if (isDrawBrackets()) {
      textLineWidth += 2 * BRACKET_WIDTH;
    }
    return textLineWidth;
  }

  public boolean isEditable() {
    return getStyle().get(StyleAttributes.EDITABLE);
  }

  public void setEditable(boolean editable) {
    getStyle().set(StyleAttributes.EDITABLE, editable);
  }

  @Override
  public void setErrorState(boolean errorState) {
    super.setErrorState(errorState);
    if (errorState) {
      myTextLine.showErrorColor();
      myNullTextLine.showErrorColor();
    } else {
      myTextLine.showTextColor();
      myNullTextLine.showTextColor();
    }
  }

  @Override
  public void relayoutImpl() {
    if (isPunctuationLayout()) {
      getStyle().set(StyleAttributes.PADDING_LEFT, new Padding(0.0));
      getStyle().set(StyleAttributes.PADDING_RIGHT, new Padding(0.0));
    }

    if (myNoTextSet && myTextLine.getText().length() == 0) {
      myNullTextLine.relayout();
      myHeight = myNullTextLine.getHeight();
      myWidth = myNullTextLine.getWidth();
    } else {
      myTextLine.relayout();
      myHeight = myTextLine.getHeight();
      myWidth = myTextLine.getWidth();
    }
  }

  @Override
  public void switchCaretVisible() {
    myCaretState.tick();
    repaintCaret();
  }

  @Override
  public void setCaretVisible(boolean visible) {
    myCaretState.touch(visible);
    repaintCaret();
  }

  private void repaintCaret() {
    getRenderedTextLine().repaintCaret(getEditor(), getShiftX(), getY());
  }

  @Override
  protected boolean isSelectionPainted() {
    return isSelected() && getEditor().getSelectionManager().getSelection() instanceof MultipleSelection;
  }

  @Override
  protected void paintContent(Graphics g, ParentSettings parentSettings) {
    TextLine textLine = getRenderedTextLine();
    boolean toShowCaret = toShowCaret();
    boolean selected = isSelectionPaintedOnAncestor(parentSettings).isSelectionPainted();
    textLine.setSelected(selected);
    textLine.setShowCaret(toShowCaret);
    Color cellFontColor = getEditor().getAdditionalCellFontColor(this);
    textLine.paint(g, getShiftX(), myY, cellFontColor);
  }

  @Override
  public void paintSelection(Graphics g, Color c, boolean drawBorder, ParentSettings parentSettings) {
    if (!isSelectionPaintedOnAncestor(parentSettings).isSelectionPainted() && getEditor().getAdditionalCellFontColor(this) != null) {
      /*
       * Suppressing selection painting in case this cell is not actually selected and additionalCellFontColor() for it is not null.
       * This will hide messages feedback if there is an AdditionalPainter instance (with specified cellFontColor) covering this cell.
       * Probably it's good idea to use separate property (not cellFontColor) to determine if this AdditionalPainter is "hiding" messages feedback
       * or simply let some additional painters paint background below and above editor messages.
       */
      return;
    }
    super.paintSelection(g, c, drawBorder, parentSettings);
  }

  protected boolean toShowCaret() {
    return myCaretState.isVisible() && isWithinSelection();
  }

  TextLine getTextLine() {
    return myTextLine;
  }

  TextLine getNullTextLine() {
    return myNullTextLine;
  }

  public TextLine getRenderedTextLine() {
    TextLine textLine;
    if (myNoTextSet && myTextLine.getText().length() == 0) {
      textLine = myNullTextLine;
    } else {
      textLine = myTextLine;
    }
    return textLine;
  }

  @Override
  public void setCaretX(int x) {
    myTextLine.setCaretByXCoord(x - getShiftX());
    makePositionValid();
  }

  @Override
  public int getCaretX() {
    return myTextLine.getCaretX(getShiftX());
  }

  private int getShiftX() {
    return isDrawBrackets() ? myX + myGapLeft + BRACKET_WIDTH : myX + myGapLeft;
  }

  private void makePositionValid() {
    if (myTextLine.getCaretPosition() == 0 && !StyleAttributesUtil.isFirstPositionAllowed(getStyle()) && isCaretPositionAllowed(1)) {
      setCaretPosition(1);
    }
    if (myTextLine.getCaretPosition() == getText().length() && !StyleAttributesUtil.isLastPositionAllowed(getStyle()) &&
        isCaretPositionAllowed(getText().length() - 1)) {
      setCaretPosition(getText().length() - 1);
    }
    getEditor().getBracesHighlighter().updateBracesSelection(this);
  }

  @Override
  public boolean processMousePressed(MouseEvent e) {
    setCaretX(e.getX());
    getEditor().repaint(this);
    return true;
  }

  public void ensureCaretVisible() {
    getEditor().scrollRectToVisible(new Rectangle(getCaretX() - 2 * myTextLine.charWidth(), myY, 4 * myTextLine.charWidth(), myHeight));
  }

  @Override
  protected boolean doProcessKeyTyped(final KeyEvent keyEvent, final boolean allowErrors) {
    if (!isTextTypedEvent(keyEvent)) {
      return false;
    }

    int caretPosition = getCaretPosition();
    CellSide side;
    if (caretPosition == 0) {
      side = CellSide.LEFT;
    } else if (caretPosition == getRenderedText().length()) {
      side = CellSide.RIGHT;
    } else {
      side = null;
    }

    ModelAccess modelAccess = getContext().getRepository().getModelAccess();
    String text = String.valueOf(keyEvent.getKeyChar());
    if (isEditable()) {
      IntelligentCellProcessor cellProcessor = IntelligentInputUtil.getIntelligentCellProcessor(this, getContext(), side);
      ModifyTextCommand keyTypedCommand =
          new ModifyTextCommand(text, allowErrors, side, getContext(), cellProcessor);
      modelAccess.executeCommand(keyTypedCommand);
      getEditor().relayout();
      return keyTypedCommand.getResult();
    } else if (side != null) {
      String pattern = getUpdatedText(text);
      if (!pattern.equals(getRenderedText())) {
        return IntelligentInputUtil.processCell(this, getContext(), pattern, side);
      }
    }
    return false;
  }

  @Override
  public boolean processTextChanged(TextChangeEvent textChangeEvent) {
    if (!isEditable()) {
      return false;
    }

    if (myTextLine.hasNonTrivialSelection()) {
      myTextLine.resetSelection();
    }
    int selectionStart = Math.max(getCaretPosition() - textChangeEvent.getOffset(), 0);
    while (!isCaretPositionAllowed(selectionStart) && selectionStart < getCaretPosition()) {
      selectionStart++;
    }
    if (selectionStart < getCaretPosition()) {
      setCaretPosition(selectionStart, true);
    }

    ModifyTextCommand keyTypedCommand = new ModifyTextCommand(textChangeEvent.getText(), true, getContext());
    getContext().getRepository().getModelAccess().executeCommand(keyTypedCommand);
    if (keyTypedCommand.getResult()) {
      getEditor().relayout();
    }
    return keyTypedCommand.getResult();
  }

  private void addChangeTextUndoableAction() {
    UndoHelper.getInstance().addUndoableAction(new DummyUndoableAction(getSNode()));
  }

  @Override
  public boolean executeTextAction(CellActionType type, boolean allowErrors) {
    assert type == CellActionType.DELETE || type == CellActionType.BACKSPACE;
    if (!isEditable()) {
      return false;
    }
    // TODO: check if we need command here or we can execute command from UI action...
    ProcessTextActionCommand textAction = new ProcessTextActionCommand(getContext(), type, allowErrors);
    getContext().getRepository().getModelAccess().executeCommand(textAction);
    return textAction.getResult();
  }

  /**
   * @param replacingText the text, replacing selected fragment
   * @return the string contained in myTextLine, updated by replacing selected text fragment with the replacingText parameter
   */
  private String getUpdatedText(String replacingText) {
    String currentText = myTextLine.getText();
    int startSelection = myTextLine.getStartTextSelectionPosition();
    int endSelection = myTextLine.getEndTextSelectionPosition();
    return currentText.substring(0, startSelection) + replacingText + currentText.substring(endSelection);
  }

  private boolean canDeleteFrom(jetbrains.mps.openapi.editor.cells.EditorCell cell) {
    if (getText().length() == 0) {
      return false;
    }
    if (!(cell instanceof EditorCell_Label)) {
      return false;
    }
    EditorCell_Label label = (EditorCell_Label) cell;
    return label.isEditable() && label.isSelectable();
  }

  private void deleteIfPossible(CellActionType actionType) {
    assert CellActionType.DELETE == actionType || CellActionType.BACKSPACE == actionType;
    if (getText() != null && getText().isEmpty() && getStyle().get(StyleAttributes.AUTO_DELETABLE)) {
      // TODO: just use delete action (do not call getSNode().delete()) in the end if acton was not found or is not applicable
      getEditorComponent().getActionHandler().executeAction(this, actionType);
    }
  }

  @Override
  public String getSelectedText() {
    return myTextLine.getTextuallySelectedText();
  }

  @Override
  public int getSelectionStart() {
    return myTextLine.getStartTextSelectionPosition();
  }

  @Override
  public void setSelectionStart(int position) {
    myTextLine.setStartTextSelectionPosition(position);
  }

  @Override
  public int getSelectionEnd() {
    return myTextLine.getEndTextSelectionPosition();
  }

  @Override
  public void setSelectionEnd(int position) {
    myTextLine.setEndTextSelectionPosition(position);
  }

  public void deleteSelection() {
    String myText = myTextLine.getText();
    int stSel = myTextLine.getStartTextSelectionPosition();
    int endSel = myTextLine.getEndTextSelectionPosition();
    changeText(myText.substring(0, stSel) + myText.substring(endSel));
    myTextLine.setCaretPosition(stSel);
    addChangeTextUndoableAction();
    fireSelectionChanged();
    ensureCaretVisible();
  }

  public void changeText(final String text) {
    String oldText = getText();
    setText(text);
    updateVfsTimestamp(text, oldText);
  }

  private void updateVfsTimestamp(String text, String oldText) {
    if (EqualUtil.equals(oldText, text) || isValidText(text)) {
      return;
    }
    if (CommandProcessor.getInstance().getCurrentCommand() == null) {
      return;
    }
    getEditorComponent().touch();
  }

  public void insertText(String text) {
    int startSelectionPosition = myTextLine.getStartTextSelectionPosition();
    int endSelectionPosition = myTextLine.getEndTextSelectionPosition();
    if (startSelectionPosition >= endSelectionPosition) {
      startSelectionPosition = myTextLine.getCaretPosition();
      endSelectionPosition = myTextLine.getCaretPosition();
    }
    String oldText = getText();
    changeText(oldText.substring(0, startSelectionPosition) + text + oldText.substring(endSelectionPosition));
    myTextLine.setCaretPosition(startSelectionPosition);
    myTextLine.setCaretPosition(startSelectionPosition + text.length(), true);
    addChangeTextUndoableAction();
  }

  public boolean isValidText(String text) {
    return true;
  }

  public void setUnderlined(boolean b) {
    getStyle().set(StyleAttributes.UNDERLINED, b);
  }

  @Override
  public int getAscent() {
    return getRenderedTextLine().getAscent();
  }

  @Override
  public int getDescent() {
    return getRenderedTextLine().getDescent();
  }

  @Override
  public NodeSubstitutePatternEditor createSubstitutePatternEditor() {
    NodeSubstitutePatternEditor pattern = new NodeSubstitutePatternEditor();
    pattern.setText(getText());
    pattern.setCaretPosition(getCaretPosition());
    return pattern;
  }

  public void selectWordOrAll() {
    if (getTextLine().getStartTextSelectionPosition() != getTextLine().getEndTextSelectionPosition()) {
      selectAll();
      return;
    }

    int start = getPrevLocalHome(false);
    int end = getNextLocalEnd(false);

    if (start != end) {
      select(start, end);
    } else {
      selectAll();
    }

  }

  private void select(int start, int end) {
    assert start <= end;
    setSelectionStart(start);
    setSelectionEnd(end);
  }

  private int getNextLocalEnd(boolean skipLeadingSpaces) {
    int length = getText().length();
    assert getCaretPosition() <= length;
    for (int i = getCaretPosition(); i != length; ++i) {

      if (Character.isWhitespace(getText().charAt(i))) {
        if (skipLeadingSpaces) {
          if (i == length - 1 || !Character.isWhitespace(getText().charAt(i + 1))) {
            return i + 1;
          }
        } else {
          return i;
        }
      }
    }
    return length;
  }

  private int getPrevLocalHome(boolean skipLeadingSpaces) {
    assert getCaretPosition() >= 0;

    for (int i = getCaretPosition(); i >= 1; --i) {
      char c = getText().charAt(i - 1);
      if (Character.isWhitespace(c) && !skipLeadingSpaces) {
        return i;
      }

      if (!Character.isWhitespace(c)) {
        skipLeadingSpaces = false;
      }
    }
    return 0;
  }

  public void selectAll() {
    getTextLine().selectAll();
  }

  @Override
  public void deselectAll() {
    getTextLine().deselectAll();
  }

  public boolean isEverythingSelected() {
    return getTextLine().isEverythingSelected();
  }

  @Override
  public SubstituteInfo getSubstituteInfo() {
    SubstituteInfo substituteInfo = super.getSubstituteInfo();
    if (substituteInfo != null) {
      substituteInfo.setOriginalText(getText() == null || getText().isEmpty() ? getNullText() : getText());
    }
    return substituteInfo;
  }

  public String toString() {
    return NameUtil.shortNameFromLongName(getClass().getName()) + "[text=" + getText() + "]";
  }

  @Override
  public TextBuilder renderText() {
    return new TextBuilderImpl(getRenderedText());
  }

  public int getCharWidth() {
    return getRenderedTextLine().charWidth();
  }

  public String getTextBeforeCaret() {
    return myTextLine.getTextBeforeCaret();
  }

  public String getTextAfterCaret() {
    return myTextLine.getTextAfterCaret();
  }

  private void fireSelectionChanged() {
    getEditorComponent().getSelectionManager().setSelection(getEditorComponent().getSelectionManager().getSelection());
  }

  private boolean isTheOnlyCompletelySelectedLabelInBigCell() {
    jetbrains.mps.openapi.editor.cells.EditorCell containingBigCell = CellTraversalUtil.getContainingBigCell(this);
    return CellTraversalUtil.getFirstLeaf(containingBigCell) == this && CellTraversalUtil.getLastLeaf(containingBigCell) == this &&
           getText().equals(getSelectedText());
  }

  public String getCommandGroupId() {
    return getCellId() + "_" + String.valueOf(getSNodeId());
  }

  private class MoveLeft extends AbstractCellAction {
    private boolean myWithSelection;

    private MoveLeft(boolean withSelection) {
      myWithSelection = withSelection;
    }

    @Override
    public boolean canExecute(EditorContext context) {
      return isCaretPositionAllowed(getCaretPosition() - 1);
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(getCaretPosition() - 1, myWithSelection);
      fireSelectionChanged();
      ensureCaretVisible();
    }
  }

  private class MoveRight extends AbstractCellAction {
    private boolean myWithSelection;

    private MoveRight(boolean withSelection) {
      myWithSelection = withSelection;
    }

    @Override
    public boolean canExecute(EditorContext context) {
      return isCaretPositionAllowed(getCaretPosition() + 1);
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(getCaretPosition() + 1, myWithSelection);
      fireSelectionChanged();
      ensureCaretVisible();
    }
  }

  private class SelectHome extends AbstractCellAction {
    @Override
    public boolean canExecute(EditorContext context) {
      return isCaretPositionAllowed(0);
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(0, true);
      fireSelectionChanged();
      ensureCaretVisible();
    }
  }

  private class SelectEnd extends AbstractCellAction {
    @Override
    public boolean canExecute(EditorContext context) {
      return isCaretPositionAllowed(getText().length());
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(getText().length(), true);
      fireSelectionChanged();
      ensureCaretVisible();
    }
  }

  private class CopyLabelText extends AbstractCellAction {
    @Override
    public boolean canExecute(EditorContext context) {
      SelectionManager selectionManager = context.getEditorComponent().getSelectionManager();
      if (selectionManager.getSelection() instanceof EditorCellLabelSelection) {
        EditorCellLabelSelection labelSelection = (EditorCellLabelSelection) selectionManager.getSelection();
        return labelSelection.getEditorCellLabel().getSelectedText().length() > 0;
      }
      return false;
    }

    @Override
    public void execute(EditorContext context) {
      // TODO: use EditorCell_Label.this. instead..
      EditorCell_Label label = (EditorCell_Label) context.getSelectedCell();
      if (label.isTheOnlyCompletelySelectedLabelInBigCell()) {
        CopyPasteUtil.copyTextAndNodeToClipboard(label.getSelectedText(), getSNode());
      } else {
        CopyPasteUtil.copyTextToClipboard(label.getSelectedText());
      }
    }
  }

  private class LocalHome extends AbstractCellAction {
    private boolean mySelect;

    private LocalHome(boolean select) {
      mySelect = select;
    }

    @Override
    public boolean canExecute(EditorContext context) {
      return !isFirstCaretPosition() && (StyleAttributesUtil.isFirstPositionAllowed(getStyle()) || getPrevLocalHome(true) != 0);
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(getPrevLocalHome(true), mySelect);
    }

  }

  private class LocalEnd extends AbstractCellAction {
    private boolean mySelect;

    private LocalEnd(boolean select) {
      mySelect = select;
    }

    @Override
    public boolean canExecute(EditorContext context) {
      return !isLastCaretPosition() && (StyleAttributesUtil.isLastPositionAllowed(getStyle()) || getNextLocalEnd(true) != getText().length());
    }

    @Override
    public void execute(EditorContext context) {
      setCaretPosition(getNextLocalEnd(true), mySelect);
    }
  }

  private class ClearSelection extends AbstractCellAction {

    @Override
    public boolean canExecute(EditorContext context) {
      return myTextLine.hasNonTrivialSelection();
    }

    @Override
    public void execute(EditorContext context) {
      myTextLine.resetSelection();
    }
  }

  private class PasteIntoLabelText extends AbstractCellAction {
    @Override
    public boolean canExecute(EditorContext context) {
      if (!(context.getSelectedCell() instanceof EditorCell_Label)) {
        return false;
      }
      // TODO: use EditorCell_Label.this. instead..
      EditorCell_Label label = (EditorCell_Label) context.getSelectedCell();
      SNode node = label.getSNode();

      // If selected cell is:
      // - the only completely selected label in big cell
      // - the cursor is in the beginning of this cell
      // - the cursor is in the end of this cell
      // then we paste text into the cell only if it is on top of clipboard (text was copied from another cell)
      // otherwise in this case this action will not be applicable, so node paste action should perform actual pasting
      //
      // if non of above is true then just pasting text from the clipboard into this cell (e.g. you can copy 1 + 2 and
      // paste it into the name label).
      return node != null && label.canPasteText() && label.isEditable() &&
             (label.isTheOnlyCompletelySelectedLabelInBigCell() || isFirstCaretPosition() && !getTextLine().hasNonTrivialSelection() ||
              isLastCaretPosition() && !getTextLine().hasNonTrivialSelection() ? CopyPasteUtil.isStringOnTopOfClipboard() :
              TextPasteUtil.hasStringInClipboard());
    }

    @Override
    public void execute(EditorContext context) {
      EditorCell_Label cell = (EditorCell_Label) context.getSelectedCell();
      final String s = TextPasteUtil.getStringFromClipboard();
      cell.insertText(NameUtil.escapeInvisibleCharacters(s));
      fireSelectionChanged();
      cell.ensureCaretVisible();
    }
  }

  private class CutLabelText extends AbstractCellAction {
    @Override
    public boolean canExecute(EditorContext context) {
      SelectionManager selectionManager = context.getEditorComponent().getSelectionManager();
      if (selectionManager.getSelection() instanceof EditorCellLabelSelection) {
        EditorCellLabelSelection labelSelection = (EditorCellLabelSelection) selectionManager.getSelection();
        return labelSelection.getEditorCellLabel().getSelectedText().length() > 0;
      }
      return false;
    }

    @Override
    public void execute(EditorContext context) {
      // TODO: use EditorCell_Label.this. instead..
      EditorCell_Label label = (EditorCell_Label) context.getSelectedCell();
      if (label.isTheOnlyCompletelySelectedLabelInBigCell()) {
        CopyPasteUtil.copyTextAndNodeToClipboard(label.getSelectedText(), getSNode());
      } else {
        CopyPasteUtil.copyTextToClipboard(label.getSelectedText());
      }
      if (label.canPasteText()) {
        label.deleteSelection();
      }
    }
  }

  private class ProcessTextActionCommand extends EditorComputable<Boolean> implements UndoRunnable {

    private CellActionType myActionType;
    private boolean myAllowErrors;

    ProcessTextActionCommand(EditorContext context, CellActionType type, boolean allowErrors) {
      super(context);
      myActionType = type;
      myAllowErrors = allowErrors;
    }

    @Override
    public Boolean doCompute() {
      String oldText = myTextLine.getText();
      int caretPosition = myTextLine.getCaretPosition();

      if (myActionType == CellActionType.BACKSPACE) {
        if (myTextLine.hasNonTrivialSelection()) {
          deleteSelection();
          deleteIfPossible(myActionType);
          return true;
        }

        if (caretPosition > 0) {
          String newText = oldText.substring(0, caretPosition - 1) + oldText.substring(caretPosition);
          if (!myAllowErrors && !isValidText(newText)) {
            return false;
          }
          changeText(newText);
          addChangeTextUndoableAction();
          if (!isCaretPositionAllowed(caretPosition - 1)) {
            return false;
          }
          setCaretPosition(caretPosition - 1);
          ensureCaretVisible();
          deleteIfPossible(myActionType);
          return true;
        } else {
          jetbrains.mps.openapi.editor.cells.EditorCell prevLeaf = CellTraversalUtil.getPrevLeaf(EditorCell_Label.this);
          if (myAllowErrors && canDeleteFrom(prevLeaf)) {
            EditorCell_Label label = (EditorCell_Label) prevLeaf;
            getEditor().changeSelection(label);
            label.end();
            label.executeTextAction(myActionType, true);
            return true;
          }
          return false;
        }
      } else if (myActionType == CellActionType.DELETE) {
        if (myTextLine.hasNonTrivialSelection()) {
          deleteSelection();
          deleteIfPossible(myActionType);
          return true;
        } else if (caretPosition < oldText.length()) {
          String newText = oldText.substring(0, caretPosition) + oldText.substring(caretPosition + 1);
          if (!myAllowErrors && !isValidText(newText)) {
            return false;
          }
          changeText(newText);
          addChangeTextUndoableAction();
          ensureCaretVisible();
          deleteIfPossible(myActionType);
          return true;
        } else {
          jetbrains.mps.openapi.editor.cells.EditorCell nextLeaf = CellTraversalUtil.getNextLeaf(EditorCell_Label.this);
          if (myAllowErrors && canDeleteFrom(nextLeaf)) {
            EditorCell_Label label = (EditorCell_Label) nextLeaf;
            getEditor().changeSelection(label);
            label.home();
            label.executeTextAction(myActionType, true);
            return true;
          }
          return false;
        }
      }

      return false;
    }

    @Nullable
    @Override
    public String getName() {
      return null;
    }

    @Nullable
    @Override
    public String getGroupId() {
      return getCommandGroupId();
    }

    @Override
    public boolean shallConfirmUndo() {
      return false;
    }
  }

  public class ModifyTextCommand extends EditorComputable<Boolean> implements UndoRunnable {
    private final String myReplacingText;
    private final boolean myAllowErrors;

    @Nullable
    private final CellSide mySide;
    @Nullable
    private final IntelligentCellProcessor myIntelligentCellProcessor;

    public ModifyTextCommand(String replacingText, boolean allowErrors, @Nullable CellSide side, EditorContext context,
                             @Nullable IntelligentCellProcessor cellProcessor) {
      super(context);
      myReplacingText = replacingText;
      myAllowErrors = allowErrors;
      mySide = side;
      myIntelligentCellProcessor = cellProcessor;
    }

    public ModifyTextCommand(String replacingText, boolean allowErrors, EditorContext context) {
      this(replacingText, allowErrors, null, context, null);
    }

    @Override
    protected Boolean doCompute() {
      if (processMutableKeyTyped(myReplacingText, myAllowErrors)) {
        getContext().flushEvents();
        addChangeTextUndoableAction();

        if (isErrorState() && myIntelligentCellProcessor != null && myIntelligentCellProcessor.processCell(getRenderedText())) {
          /**
           * Resetting current command group ID if cell was side-transformed. In such situations
           * side-transforming command as well as char typing command should be separate part of
           * undo-redo process, not connected with eytyping events which are grouped together.
           */
          CommandProcessor.getInstance().setCurrentCommandGroupId(null);
        }
        return true;
      }
      return isErrorState() && mySide == CellSide.LEFT && " ".equals(myReplacingText);
    }

    private boolean processMutableKeyTyped(String replacement, final boolean allowErrors) {
      String newText = getUpdatedText(replacement);
      if (!allowErrors && !isValidText(newText)) {
        return false;
      }

      int startSelection = myTextLine.getStartTextSelectionPosition();
      changeText(newText);
      setCaretPositionIfPossible(startSelection + myReplacingText.length());
      myTextLine.resetSelection();
      fireSelectionChanged();
      ensureCaretVisible();
      return true;
    }

    @Nullable
    @Override
    public String getName() {
      return null;
    }

    @Nullable
    @Override
    public String getGroupId() {
      return getCommandGroupId();
    }

    @Override
    public boolean shallConfirmUndo() {
      return false;
    }
  }

  /**
   * This action can be used to introduce empty action into the stack of actions within UndoHelper
   * forcing it to add undoable command into IDEA undo stack: see {@link jetbrains.mps.ide.undo.WorkbenchUndoHandler}
   * flushCommand() method implementation. This method will not add {@link jetbrains.mps.ide.undo.SNodeIdeaUndoableAction}
   * action into IDEA undo stack if it has no own undoable actions.
   * <p/>
   * This is helpful in case of UI-only modifications performed upon the cells. For example, if the textual cell is modified
   * so it represents invalid value. In this case this value cannot be committed into the model and will stay in the editor
   * cell/memento objects only. Empty command in this case will add a "mark" in IDEA undo stack, so corresponding editor
   * memento will be restored on udo/redo of this empty command.
   */
  protected static class DummyUndoableAction extends SNodeUndoableAction {
    protected DummyUndoableAction(SNode node) {
      super(node);
    }

    @Override
    protected void doUndo() {
    }

    @Override
    protected void doRedo() {
    }
  }
}
