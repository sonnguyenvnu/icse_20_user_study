/** 
 * Extract breakpointed lines from source code marker comments. This removes marker comments from the editor text. Intended to be called on loading a sketch, since re-setting the sketches contents after removing the markers will clear all breakpoints.
 * @return the list of {@link LineID}s where breakpoint marker comments were removed from.
 */
protected List<LineID> stripBreakpointComments(){
  List<LineID> bps=new ArrayList<>();
  Sketch sketch=getSketch();
  for (int i=0; i < sketch.getCodeCount(); i++) {
    SketchCode tab=sketch.getCode(i);
    String code=tab.getProgram();
    String lines[]=code.split("\\r?\\n");
    int lineIdx=0;
    for (    String line : lines) {
      if (line.endsWith(breakpointMarkerComment)) {
        LineID lineID=new LineID(tab.getFileName(),lineIdx);
        bps.add(lineID);
        int index=line.lastIndexOf(breakpointMarkerComment);
        lines[lineIdx]=line.substring(0,index);
      }
      lineIdx++;
    }
    code=PApplet.join(lines,"\n");
    setTabContents(tab.getFileName(),code);
  }
  return bps;
}
