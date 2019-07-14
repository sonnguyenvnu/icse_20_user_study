/** 
 * Set the gutter text of a specific line.
 * @param lineIdx the line index (0-based)
 * @param text the text
 */
public void setGutterText(int lineIdx,String text){
  gutterText.put(lineIdx,text);
  painter.invalidateLine(lineIdx);
}
