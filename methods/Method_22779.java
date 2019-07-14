/** 
 * Updates the state of the scroll bars. This should be called if the number of lines in the document changes, or when the size of the text area changes.
 */
public void updateScrollBars(){
  if (vertical != null && visibleLines != 0) {
    vertical.setValues(firstLine,visibleLines,0,getLineCount());
    vertical.setUnitIncrement(2);
    vertical.setBlockIncrement(visibleLines);
  }
  if ((horizontal != null) && (painter.getWidth() != 0)) {
    int lineCount=getLineCount();
    int maxLineLength=0;
    for (int i=0; i < lineCount; i++) {
      int lineLength=getLineLength(i);
      if (lineLength > maxLineLength) {
        maxLineLength=lineLength;
      }
    }
    int charWidth=painter.getFontMetrics().charWidth('w');
    int width=maxLineLength * charWidth;
    int painterWidth=painter.getScrollWidth();
    if (horizontalOffset < 0) {
      horizontal.setValues(-horizontalOffset,painterWidth,-leftHandGutter,width);
    }
 else {
      horizontal.setValues(-leftHandGutter,painterWidth,-leftHandGutter,width);
    }
    horizontal.setUnitIncrement(charWidth);
    horizontal.setBlockIncrement(width / 2);
  }
}
