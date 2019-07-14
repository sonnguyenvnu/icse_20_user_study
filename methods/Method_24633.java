/** 
 * Print call stack trace of a thread. Only works on suspended threads.
 * @param t suspended thread to print stack trace of
 */
protected void printStackTrace(ThreadReference t){
  if (!t.isSuspended()) {
    return;
  }
  try {
    System.out.println("stack trace for thread " + t.name() + ":");
    int i=0;
    for (    StackFrame f : t.frames()) {
      System.out.println(i++ + ": " + f.toString());
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
}
