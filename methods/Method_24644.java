/** 
 * Print visible fields of current "this" object on a suspended thread. Prints type, name and value.
 * @param t suspended thread
 */
protected void printThis(ThreadReference t){
  if (!t.isSuspended()) {
    return;
  }
  try {
    if (t.frameCount() == 0) {
      System.out.println("call stack empty");
    }
 else {
      StackFrame sf=t.frame(0);
      ObjectReference thisObject=sf.thisObject();
      if (thisObject != null) {
        ReferenceType type=thisObject.referenceType();
        System.out.println("fields in this (" + type.name() + "):");
        for (        Field f : type.visibleFields()) {
          System.out.println(f.typeName() + " " + f.name() + " = " + thisObject.getValue(f));
        }
      }
 else {
        System.out.println("can't get this (in native or static method)");
      }
    }
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
}
