@Override public SofaTracerSpan pop() throws EmptyStackException {
  if (this.isEmpty()) {
    return null;
  }
  SofaTracerSpan sofaTracerSpan=threadLocal.get();
  this.clear();
  return sofaTracerSpan;
}
