/** 
 * Remove a breakpoint from a line in the current tab.
 * @param lineIdx the line index (0-based) in the current tab to remove thebreakpoint from
 */
void removeBreakpoint(int lineIdx){
  if (isBusy()) {
    return;
  }
  LineBreakpoint bp=breakpointOnLine(editor.getLineIDInCurrentTab(lineIdx));
  if (bp != null) {
    bp.remove();
    breakpoints.remove(bp);
    log("removed breakpoint " + bp);
  }
}
