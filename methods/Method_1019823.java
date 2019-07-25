@Override public void push(SofaTracerSpan span){
  if (span == null) {
    return;
  }
  threadLocal.set(span);
}
