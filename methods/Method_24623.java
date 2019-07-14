/** 
 * Toggle a breakpoint on a line in the current tab.
 * @param lineIdx the line index (0-based) in the current tab
 */
synchronized void toggleBreakpoint(int lineIdx){
  LineID line=editor.getLineIDInCurrentTab(lineIdx);
  int index=line.lineIdx();
  if (hasBreakpoint(line)) {
    removeBreakpoint(index);
  }
 else {
    if (editor.getLineText(index).trim().length() != 0) {
      setBreakpoint(index);
    }
  }
}
