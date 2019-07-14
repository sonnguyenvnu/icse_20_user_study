/** 
 * Resize the screen which this transcript backs. Currently, this only works if the number of columns does not change or the rows expand (that is, it only works when shrinking the number of rows).
 * @param newColumns The number of columns the screen should have.
 * @param newRows    The number of rows the screen should have.
 * @param cursor     An int[2] containing the (column, row) cursor location.
 */
public void resize(int newColumns,int newRows,int newTotalRows,int[] cursor,long currentStyle,boolean altScreen){
  if (newColumns == mColumns && newRows <= mTotalRows) {
    int shiftDownOfTopRow=mScreenRows - newRows;
    if (shiftDownOfTopRow > 0 && shiftDownOfTopRow < mScreenRows) {
      for (int i=mScreenRows - 1; i > 0; i--) {
        if (cursor[1] >= i)         break;
        int r=externalToInternalRow(i);
        if (mLines[r] == null || mLines[r].isBlank()) {
          if (--shiftDownOfTopRow == 0)           break;
        }
      }
    }
 else     if (shiftDownOfTopRow < 0) {
      int actualShift=Math.max(shiftDownOfTopRow,-mActiveTranscriptRows);
      if (shiftDownOfTopRow != actualShift) {
        for (int i=0; i < actualShift - shiftDownOfTopRow; i++)         allocateFullLineIfNecessary((mScreenFirstRow + mScreenRows + i) % mTotalRows).clear(currentStyle);
        shiftDownOfTopRow=actualShift;
      }
    }
    mScreenFirstRow+=shiftDownOfTopRow;
    mScreenFirstRow=(mScreenFirstRow < 0) ? (mScreenFirstRow + mTotalRows) : (mScreenFirstRow % mTotalRows);
    mTotalRows=newTotalRows;
    mActiveTranscriptRows=altScreen ? 0 : Math.max(0,mActiveTranscriptRows + shiftDownOfTopRow);
    cursor[1]-=shiftDownOfTopRow;
    mScreenRows=newRows;
  }
 else {
    TerminalRow[] oldLines=mLines;
    mLines=new TerminalRow[newTotalRows];
    for (int i=0; i < newTotalRows; i++)     mLines[i]=new TerminalRow(newColumns,currentStyle);
    final int oldActiveTranscriptRows=mActiveTranscriptRows;
    final int oldScreenFirstRow=mScreenFirstRow;
    final int oldScreenRows=mScreenRows;
    final int oldTotalRows=mTotalRows;
    mTotalRows=newTotalRows;
    mScreenRows=newRows;
    mActiveTranscriptRows=mScreenFirstRow=0;
    mColumns=newColumns;
    int newCursorRow=-1;
    int newCursorColumn=-1;
    int oldCursorRow=cursor[1];
    int oldCursorColumn=cursor[0];
    boolean newCursorPlaced=false;
    int currentOutputExternalRow=0;
    int currentOutputExternalColumn=0;
    int skippedBlankLines=0;
    for (int externalOldRow=-oldActiveTranscriptRows; externalOldRow < oldScreenRows; externalOldRow++) {
      int internalOldRow=oldScreenFirstRow + externalOldRow;
      internalOldRow=(internalOldRow < 0) ? (oldTotalRows + internalOldRow) : (internalOldRow % oldTotalRows);
      TerminalRow oldLine=oldLines[internalOldRow];
      boolean cursorAtThisRow=externalOldRow == oldCursorRow;
      if (oldLine == null || (!(!newCursorPlaced && cursorAtThisRow)) && oldLine.isBlank()) {
        skippedBlankLines++;
        continue;
      }
 else       if (skippedBlankLines > 0) {
        for (int i=0; i < skippedBlankLines; i++) {
          if (currentOutputExternalRow == mScreenRows - 1) {
            scrollDownOneLine(0,mScreenRows,currentStyle);
          }
 else {
            currentOutputExternalRow++;
          }
          currentOutputExternalColumn=0;
        }
        skippedBlankLines=0;
      }
      int lastNonSpaceIndex=0;
      boolean justToCursor=false;
      if (cursorAtThisRow || oldLine.mLineWrap) {
        lastNonSpaceIndex=oldLine.getSpaceUsed();
        if (cursorAtThisRow)         justToCursor=true;
      }
 else {
        for (int i=0; i < oldLine.getSpaceUsed(); i++)         if (oldLine.mText[i] != ' ')         lastNonSpaceIndex=i + 1;
      }
      int currentOldCol=0;
      long styleAtCol=0;
      for (int i=0; i < lastNonSpaceIndex; i++) {
        char c=oldLine.mText[i];
        int codePoint=(Character.isHighSurrogate(c)) ? Character.toCodePoint(c,oldLine.mText[++i]) : c;
        int displayWidth=WcWidth.width(codePoint);
        if (displayWidth > 0)         styleAtCol=oldLine.getStyle(currentOldCol);
        if (currentOutputExternalColumn + displayWidth > mColumns) {
          setLineWrap(currentOutputExternalRow);
          if (currentOutputExternalRow == mScreenRows - 1) {
            if (newCursorPlaced)             newCursorRow--;
            scrollDownOneLine(0,mScreenRows,currentStyle);
          }
 else {
            currentOutputExternalRow++;
          }
          currentOutputExternalColumn=0;
        }
        int offsetDueToCombiningChar=((displayWidth <= 0 && currentOutputExternalColumn > 0) ? 1 : 0);
        int outputColumn=currentOutputExternalColumn - offsetDueToCombiningChar;
        setChar(outputColumn,currentOutputExternalRow,codePoint,styleAtCol);
        if (displayWidth > 0) {
          if (oldCursorRow == externalOldRow && oldCursorColumn == currentOldCol) {
            newCursorColumn=currentOutputExternalColumn;
            newCursorRow=currentOutputExternalRow;
            newCursorPlaced=true;
          }
          currentOldCol+=displayWidth;
          currentOutputExternalColumn+=displayWidth;
          if (justToCursor && newCursorPlaced)           break;
        }
      }
      if (externalOldRow != (oldScreenRows - 1) && !oldLine.mLineWrap) {
        if (currentOutputExternalRow == mScreenRows - 1) {
          if (newCursorPlaced)           newCursorRow--;
          scrollDownOneLine(0,mScreenRows,currentStyle);
        }
 else {
          currentOutputExternalRow++;
        }
        currentOutputExternalColumn=0;
      }
    }
    cursor[0]=newCursorColumn;
    cursor[1]=newCursorRow;
  }
  if (cursor[0] < 0 || cursor[1] < 0)   cursor[0]=cursor[1]=0;
}
