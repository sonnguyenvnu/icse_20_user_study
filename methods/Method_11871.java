private void runAfters(){
  List<Method> afters=testClass.getAfters();
  for (  Method after : afters) {
    try {
      after.invoke(null);
    }
 catch (    InvocationTargetException e) {
      addFailure(e.getTargetException());
    }
catch (    Throwable e) {
      addFailure(e);
    }
  }
}
