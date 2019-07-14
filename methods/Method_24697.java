/** 
 * Add breakpoint marker comments to the source file of a specific tab. This acts on the source file on disk, not the editor text. Intended to be called just after saving the sketch.
 * @param tabFilename the tab file name
 */
protected void addBreakpointComments(String tabFilename){
  SketchCode tab=getTab(tabFilename);
  if (tab == null) {
    Messages.loge("Illegal tab name to addBreakpointComments() " + tabFilename);
    return;
  }
  List<LineBreakpoint> bps=debugger.getBreakpoints(tab.getFileName());
  try {
    tab.load();
    String code=tab.getProgram();
    String lines[]=code.split("\\r?\\n");
    for (    LineBreakpoint bp : bps) {
      lines[bp.lineID().lineIdx()]+=breakpointMarkerComment;
    }
    code=PApplet.join(lines,"\n");
    tab.setProgram(code);
    tab.save();
  }
 catch (  IOException ex) {
    Messages.loge(null,ex);
  }
}
