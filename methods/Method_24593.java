/** 
 * Attach this breakpoint to the VM. Creates and enables a {@link BreakpointRequest}. VM needs to be paused.
 * @param theClass class to attach to
 * @return true on success
 */
protected boolean attach(ReferenceType theClass){
  if (theClass == null || className == null || !className.equals(parseTopLevelClassName(theClass.name()))) {
    return false;
  }
  log("trying to attach: " + line.fileName + ":" + line.lineIdx + " to " + theClass.name());
  if (!dbg.isPaused()) {
    log("can't attach breakpoint, debugger not paused");
    return false;
  }
  LineID javaLine=dbg.sketchToJavaLine(line);
  if (javaLine == null) {
    log("couldn't find line " + line + " in the java code");
    return false;
  }
  try {
    log("BPs of class: " + theClass + ", line " + (javaLine.lineIdx() + 1));
    List<Location> locations=theClass.locationsOfLine(javaLine.lineIdx() + 1);
    if (locations.isEmpty()) {
      log("no location found for line " + line + " -> " + javaLine);
      return false;
    }
    bpr=dbg.vm().eventRequestManager().createBreakpointRequest(locations.get(0));
    bpr.enable();
    log("attached breakpoint to " + line + " -> " + javaLine);
    return true;
  }
 catch (  AbsentInformationException ex) {
    Messages.loge(null,ex);
  }
  return false;
}
