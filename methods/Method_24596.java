/** 
 * Set this breakpoint. Adds the line highlight. If Debugger is paused also attaches the breakpoint by calling  {@link #attach()}.
 */
protected void set(){
  dbg.addClassLoadListener(this);
  dbg.getEditor().addBreakpointedLine(line);
  if (className != null && dbg.isPaused()) {
    for (    ReferenceType rt : dbg.getClasses()) {
      if (attach(rt))       break;
    }
  }
  if (dbg.getEditor().isInCurrentTab(line)) {
    dbg.getEditor().getSketch().setModified(true);
  }
}
