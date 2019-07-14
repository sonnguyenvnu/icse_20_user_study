/** 
 * Get the name of the class this breakpoint belongs to. Needed for fetching the right location to create a breakpoint request.
 * @return the class name
 */
protected String className(){
  if (line.fileName().endsWith(".pde")) {
    return dbg.getEditor().getSketch().getName();
  }
  if (line.fileName().endsWith(".java")) {
    return line.fileName().substring(0,line.fileName().lastIndexOf(".java"));
  }
  return null;
}
