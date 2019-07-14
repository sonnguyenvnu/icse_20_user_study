/** 
 * A fast way of changing both the first line and horizontal offset.
 * @param firstLine The new first line
 * @param horizontalOffset The new horizontal offset
 * @return True if any of the values were changed, false otherwise
 */
public boolean setOrigin(int firstLine,int horizontalOffset){
  boolean changed=false;
  if (horizontalOffset != this.horizontalOffset) {
    this.horizontalOffset=horizontalOffset;
    changed=true;
  }
  if (firstLine != this.firstLine) {
    this.firstLine=firstLine;
    changed=true;
  }
  if (changed) {
    updateScrollBars();
    painter.repaint();
  }
  return changed;
}
