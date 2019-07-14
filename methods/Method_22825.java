/** 
 * Clear all gutter text.
 */
public void clearGutterText(){
  for (  int lineIdx : gutterText.keySet()) {
    painter.invalidateLine(lineIdx);
  }
  gutterText.clear();
}
