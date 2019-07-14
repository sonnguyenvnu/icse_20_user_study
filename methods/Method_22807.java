/** 
 * Selects from the start offset to the end offset. This is the general selection method used by all other selecting methods. The caret position will be start if start &lt; end, and end if end &gt; start.
 * @param start The start offset
 * @param end The end offset
 */
public void select(int start,int end){
  int newStart, newEnd;
  boolean newBias;
  if (start <= end) {
    newStart=start;
    newEnd=end;
    newBias=false;
  }
 else {
    newStart=end;
    newEnd=start;
    newBias=true;
  }
  if ((newStart < 0 || newEnd > getDocumentLength()) && start != end) {
    throw new IllegalArgumentException("Bounds out of" + " range: " + newStart + "," + newEnd + " [" + getDocumentLength() + "]");
  }
  if (newStart != selectionStart || newEnd != selectionEnd || newBias != biasLeft) {
    int newStartLine=getLineOfOffset(newStart);
    int newEndLine=getLineOfOffset(newEnd);
    if (painter.isBracketHighlightEnabled()) {
      if (bracketLine != -1)       painter.invalidateLine(bracketLine);
      updateBracketHighlight(end);
      if (bracketLine != -1)       painter.invalidateLine(bracketLine);
    }
    painter.invalidateLineRange(selectionStartLine,selectionEndLine);
    painter.invalidateLineRange(newStartLine,newEndLine);
    document.addUndoableEdit(new CaretUndo(selectionStart,selectionEnd));
    selectionStart=newStart;
    selectionEnd=newEnd;
    selectionStartLine=newStartLine;
    selectionEndLine=newEndLine;
    biasLeft=newBias;
    fireCaretEvent();
  }
  blink=true;
  if (!DISABLE_CARET) {
    caretTimer.restart();
  }
  magicCaret=-1;
  scrollToCaret();
}
