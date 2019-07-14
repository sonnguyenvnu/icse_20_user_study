/** 
 * Retrieve a list of breakpoint in a particular tab.
 * @param tabFilename the tab's file name
 * @return the list of breakpoints in the given tab
 */
synchronized List<LineBreakpoint> getBreakpoints(String tabFilename){
  List<LineBreakpoint> list=new ArrayList<>();
  for (  LineBreakpoint bp : breakpoints) {
    if (bp.lineID().fileName().equals(tabFilename)) {
      list.add(bp);
    }
  }
  return list;
}
