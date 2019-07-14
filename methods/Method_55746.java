static StackTraceElement[] stackWalkArray(Object[] a){
  return Arrays.stream(((StackWalker.StackFrame[])a)).map(StackWalker.StackFrame::toStackTraceElement).toArray(StackTraceElement[]::new);
}
