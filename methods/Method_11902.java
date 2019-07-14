@Override public void evaluate() throws Throwable {
  for (  FrameworkMethod before : befores) {
    invokeMethod(before);
  }
  next.evaluate();
}
