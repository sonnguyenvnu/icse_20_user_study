private void runBefores() throws FailedBefore {
  try {
    try {
      List<Method> befores=testMethod.getBefores();
      for (      Method before : befores) {
        before.invoke(test);
      }
    }
 catch (    InvocationTargetException e) {
      throw e.getTargetException();
    }
  }
 catch (  AssumptionViolatedException e) {
    throw new FailedBefore();
  }
catch (  Throwable e) {
    addFailure(e);
    throw new FailedBefore();
  }
}
