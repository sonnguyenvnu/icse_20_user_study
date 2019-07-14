/** 
 * Event handler for line number changes (due to editing). Will remove the highlight from the old line number and repaint it at the new location.
 * @param line the line that has changed
 * @param oldLineIdx the old line index (0-based)
 * @param newLineIdx the new line index (0-based)
 */
public void lineChanged(LineID line,int oldLineIdx,int newLineIdx){
  if (editor.isInCurrentTab(new LineID(line.fileName(),oldLineIdx))) {
    editor.getJavaTextArea().clearGutterText(oldLineIdx);
  }
  if (LineHighlight.isHighestPriority(this)) {
    paint();
  }
}
