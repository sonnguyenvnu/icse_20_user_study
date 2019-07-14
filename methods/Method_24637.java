/** 
 * Print local variables on a suspended thread. Takes the topmost stack frame and lists all local variables and their values.
 * @param t suspended thread
 */
protected void printLocalVariables(ThreadReference t){
  if (!t.isSuspended()) {
    return;
  }
  try {
    if (t.frameCount() == 0) {
      System.out.println("call stack empty");
    }
 else {
      StackFrame sf=t.frame(0);
      List<LocalVariable> locals=sf.visibleVariables();
      if (locals.isEmpty()) {
        System.out.println("no local variables");
        return;
      }
      for (      LocalVariable lv : locals) {
        System.out.println(lv.typeName() + " " + lv.name() + " = " + sf.getValue(lv));
      }
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
catch (  AbsentInformationException ex) {
    log("local variable information not available");
  }
}
