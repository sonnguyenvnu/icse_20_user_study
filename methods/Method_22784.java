/** 
 * Converts a line index to a y co-ordinate.
 * @param line The line
 */
public int lineToY(int line){
  FontMetrics fm=painter.getFontMetrics();
  return (line - firstLine) * fm.getHeight() - (fm.getLeading() + fm.getMaxDescent());
}
