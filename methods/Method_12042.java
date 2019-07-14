private Statement withAfterParams(Statement statement){
  List<FrameworkMethod> afters=getTestClass().getAnnotatedMethods(Parameterized.AfterParam.class);
  return afters.isEmpty() ? statement : new RunAfterParams(statement,afters);
}
