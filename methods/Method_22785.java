/** 
 * Converts a y co-ordinate to a line index.
 * @param y The y co-ordinate
 */
public int yToLine(int y){
  FontMetrics fm=painter.getFontMetrics();
  int height=fm.getHeight();
  return Math.max(0,Math.min(getLineCount() - 1,y / height + firstLine));
}
