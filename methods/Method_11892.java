private void runAfters(){
  List<Method> afters=testMethod.getAfters();
  for (  Method after : afters) {
    try {
      after.invoke(test);
    }
 catch (    InvocationTargetException e) {
      addFailure(e.getTargetException());
    }
catch (    Throwable e) {
      addFailure(e);
    }
  }
}
