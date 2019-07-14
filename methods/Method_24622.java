/** 
 * Get the breakpoint on a certain line, if set.
 * @param line the line to get the breakpoint from
 * @return the breakpoint, or null if no breakpoint is set on the specifiedline.
 */
LineBreakpoint breakpointOnLine(LineID line){
  for (  LineBreakpoint bp : breakpoints) {
    if (bp.isOnLine(line)) {
      return bp;
    }
  }
  return null;
}
