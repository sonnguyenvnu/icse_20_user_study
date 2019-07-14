/** 
 * Returns the y-offset in this component corresponding to a line in the text component.
 * @param line The line.
 * @return The y-offset.
 * @see #yToLine(int)
 */
private int lineToY(int line){
  int h=textArea.getVisibleRect().height;
  float lineCount=textArea.getLineCount();
  return (int)(((line - 1) / (lineCount - 1)) * h) - 2;
}
