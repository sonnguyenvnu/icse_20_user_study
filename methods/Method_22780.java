/** 
 * Sets the line displayed at the text area's origin without updating the scroll bars.
 */
public void setFirstLine(int firstLine){
  if (firstLine < 0 || firstLine > getLineCount()) {
    throw new IllegalArgumentException("First line out of range: " + firstLine + " [0, " + getLineCount() + "]");
  }
  if (firstLine == this.firstLine)   return;
  this.firstLine=firstLine;
  if (firstLine != vertical.getValue()) {
    updateScrollBars();
  }
  painter.repaint();
}
