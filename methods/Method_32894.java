private void doLinefeed(){
  boolean belowScrollingRegion=mCursorRow >= mBottomMargin;
  int newCursorRow=mCursorRow + 1;
  if (belowScrollingRegion) {
    if (mCursorRow != mRows - 1) {
      setCursorRow(newCursorRow);
    }
  }
 else {
    if (newCursorRow == mBottomMargin) {
      scrollDownOneLine();
      newCursorRow=mBottomMargin - 1;
    }
    setCursorRow(newCursorRow);
  }
}
