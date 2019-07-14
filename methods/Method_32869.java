/** 
 * Scroll the screen down one line. To scroll the whole screen of a 24 line screen, the arguments would be (0, 24).
 * @param topMargin    First line that is scrolled.
 * @param bottomMargin One line after the last line that is scrolled.
 * @param style        the style for the newly exposed line.
 */
public void scrollDownOneLine(int topMargin,int bottomMargin,long style){
  if (topMargin > bottomMargin - 1 || topMargin < 0 || bottomMargin > mScreenRows)   throw new IllegalArgumentException("topMargin=" + topMargin + ", bottomMargin=" + bottomMargin + ", mScreenRows=" + mScreenRows);
  blockCopyLinesDown(mScreenFirstRow,topMargin);
  blockCopyLinesDown(externalToInternalRow(bottomMargin),mScreenRows - bottomMargin);
  mScreenFirstRow=(mScreenFirstRow + 1) % mTotalRows;
  if (mActiveTranscriptRows < mTotalRows - mScreenRows)   mActiveTranscriptRows++;
  int blankRow=externalToInternalRow(bottomMargin - 1);
  if (mLines[blankRow] == null) {
    mLines[blankRow]=new TerminalRow(mColumns,style);
  }
 else {
    mLines[blankRow].clear(style);
  }
}
