/** 
 * Returns the line in the text area corresponding to a y-offset in this component.
 * @param y The y-offset.
 * @return The line.
 * @see #lineToY(int)
 */
private final int yToLine(int y){
  int line=-1;
  int h=textArea.getVisibleRect().height;
  if (y < h) {
    float at=y / (float)h;
    line=Math.round((textArea.getLineCount() - 1) * at);
  }
  return line;
}
