private static boolean isAutoCloseable(StackWalker.StackFrame element,StackWalker.StackFrame pushed){
  if (isSameMethod(element,pushed,"$closeResource")) {
    return true;
  }
  if ("closeFinally".equals(element.getMethodName()) && "AutoCloseable.kt".equals(element.getFileName())) {
    return true;
  }
  return false;
}
