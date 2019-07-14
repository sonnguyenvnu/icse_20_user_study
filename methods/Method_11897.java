@Override public void evaluate() throws Exception {
  boolean complete=false;
  try {
    next.evaluate();
    complete=true;
  }
 catch (  AssumptionViolatedException e) {
    if (!expected.isAssignableFrom(e.getClass())) {
      throw e;
    }
  }
catch (  Throwable e) {
    if (!expected.isAssignableFrom(e.getClass())) {
      String message="Unexpected exception, expected<" + expected.getName() + "> but was<" + e.getClass().getName() + ">";
      throw new Exception(message,e);
    }
  }
  if (complete) {
    throw new AssertionError("Expected exception: " + expected.getName());
  }
}
