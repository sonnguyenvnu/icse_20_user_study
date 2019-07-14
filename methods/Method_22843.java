/** 
 * Marks a range of lines as needing a repaint.
 * @param firstLine The first line to invalidate
 * @param lastLine The last line to invalidate
 */
final void invalidateLineRange(int firstLine,int lastLine){
  repaint(0,textArea.lineToY(firstLine) + fm.getMaxDescent() + fm.getLeading(),getWidth(),(lastLine - firstLine + 1) * fm.getHeight());
}
