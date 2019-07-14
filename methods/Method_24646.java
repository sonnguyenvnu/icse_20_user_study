/** 
 * Print source code snippet.
 * @param l {@link Location} object to print source code for
 */
protected void printSourceLocation(Location l){
  try {
    System.out.println("in method " + l.method() + ":");
    System.out.println(getSourceLine(l.sourcePath(),l.lineNumber(),2));
  }
 catch (  AbsentInformationException ex) {
    log("absent information",ex);
  }
}
