@Override public void evaluate() throws Throwable {
  List<Throwable> errors=new ArrayList<Throwable>();
  try {
    next.evaluate();
  }
 catch (  Throwable e) {
    errors.add(e);
  }
 finally {
    for (    FrameworkMethod each : afters) {
      try {
        invokeMethod(each);
      }
 catch (      Throwable e) {
        errors.add(e);
      }
    }
  }
  MultipleFailureException.assertEmpty(errors);
}
