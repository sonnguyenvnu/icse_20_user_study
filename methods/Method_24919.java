/** 
 * Move through a list of stack frames, searching for references to code found in the current sketch. Return with a RunnerException that contains the location of the error, or if nothing is found, just return with a RunnerException that wraps the error message itself.
 */
protected SketchException findException(String message,ObjectReference or,ThreadReference thread){
  try {
    List<StackFrame> frames=thread.frames();
    for (    StackFrame frame : frames) {
      try {
        Location location=frame.location();
        String filename=null;
        filename=location.sourceName();
        int lineNumber=location.lineNumber() - 1;
        SketchException rex=build.placeException(message,filename,lineNumber);
        if (rex != null) {
          return rex;
        }
      }
 catch (      AbsentInformationException e) {
        exception=new SketchException(message);
        exception.hideStackTrace();
        listener.statusError(exception);
      }
    }
  }
 catch (  IncompatibleThreadStateException e) {
    e.printStackTrace(sketchErr);
  }
catch (  Exception e) {
    if ("StackOverflowError".equals(message) == false) {
      e.printStackTrace(sketchErr);
    }
  }
  try {
    Method method=((ClassType)or.referenceType()).concreteMethodByName("getStackTrace","()[Ljava/lang/StackTraceElement;");
    ArrayReference result=(ArrayReference)or.invokeMethod(thread,method,new ArrayList<Value>(),ObjectReference.INVOKE_SINGLE_THREADED);
    for (    Value val : result.getValues()) {
      ObjectReference ref=(ObjectReference)val;
      method=((ClassType)ref.referenceType()).concreteMethodByName("getFileName","()Ljava/lang/String;");
      StringReference strref=(StringReference)ref.invokeMethod(thread,method,new ArrayList<Value>(),ObjectReference.INVOKE_SINGLE_THREADED);
      String filename=strref == null ? "Unknown Source" : strref.value();
      method=((ClassType)ref.referenceType()).concreteMethodByName("getLineNumber","()I");
      IntegerValue intval=(IntegerValue)ref.invokeMethod(thread,method,new ArrayList<Value>(),ObjectReference.INVOKE_SINGLE_THREADED);
      int lineNumber=intval.intValue() - 1;
      SketchException rex=build.placeException(message,filename,lineNumber);
      if (rex != null) {
        return rex;
      }
    }
    method=((ClassType)or.referenceType()).concreteMethodByName("printStackTrace","()V");
    or.invokeMethod(thread,method,new ArrayList<Value>(),ObjectReference.INVOKE_SINGLE_THREADED);
  }
 catch (  Exception e) {
    if ("StackOverflowError".equals(message) == false) {
      e.printStackTrace(sketchErr);
    }
  }
  SketchException rex=new SketchException(message);
  rex.hideStackTrace();
  return rex;
}
