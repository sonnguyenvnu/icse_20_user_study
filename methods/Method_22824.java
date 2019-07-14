/** 
 * Clear the gutter text of a specific line.
 * @param lineIdx the line index (0-based)
 */
public void clearGutterText(int lineIdx){
  gutterText.remove(lineIdx);
  painter.invalidateLine(lineIdx);
}
