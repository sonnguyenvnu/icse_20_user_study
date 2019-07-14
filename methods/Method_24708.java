/** 
 * Add highlight for a breakpointed line.
 * @param lineID the line id to highlight as breakpointed
 */
public void addBreakpointedLine(LineID lineID){
  LineHighlight hl=new LineHighlight(lineID,this);
  hl.setMarker(PdeTextArea.BREAK_MARKER);
  breakpointedLines.add(hl);
  if (currentLine != null && currentLine.getLineID().equals(lineID)) {
    currentLine.paint();
  }
}
