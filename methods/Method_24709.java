/** 
 * Remove all highlights for breakpointed lines. 
 */
public void clearBreakpointedLines(){
  for (  LineHighlight hl : breakpointedLines) {
    hl.clear();
    hl.dispose();
  }
  breakpointedLines.clear();
  getJavaTextArea().clearGutterText();
  if (currentLine != null) {
    currentLine.paint();
  }
}
