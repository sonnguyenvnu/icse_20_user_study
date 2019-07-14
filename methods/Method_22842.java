/** 
 * Marks a line as needing a repaint.
 * @param line The line to invalidate
 */
final public void invalidateLine(int line){
  repaint(0,textArea.lineToY(line) + fm.getMaxDescent() + fm.getLeading(),getWidth(),fm.getHeight());
}
