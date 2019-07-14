/** 
 * Print source code snippet of current location in a suspended thread.
 * @param t suspended thread
 */
protected void printSourceLocation(ThreadReference t){
  try {
    if (t.frameCount() == 0) {
      System.out.println("call stack empty");
    }
 else {
      Location l=t.frame(0).location();
      printSourceLocation(l);
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
}
