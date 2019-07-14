/** 
 * Set the line to highlight as currently suspended at. Will override the breakpoint color, if set. Switches to the appropriate tab and scroll to the line by placing the cursor there.
 * @param line the line to highlight as current suspended line
 */
public void setCurrentLine(LineID line){
  clearCurrentLine();
  if (line == null) {
    return;
  }
  switchToTab(line.fileName());
  cursorToLineStart(line.lineIdx());
  currentLine=new LineHighlight(line.lineIdx(),this);
  currentLine.setMarker(PdeTextArea.STEP_MARKER);
  currentLine.setPriority(10);
}
