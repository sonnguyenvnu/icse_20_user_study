/** 
 * Check if there's a breakpoint on a particular line.
 * @param line the line id
 * @return true if a breakpoint is set on the given line, otherwise false
 */
protected boolean hasBreakpoint(LineID line){
  LineBreakpoint bp=breakpointOnLine(line);
  return bp != null;
}
