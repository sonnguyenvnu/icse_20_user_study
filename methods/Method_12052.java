/** 
 * Returns a  {@link Statement}: run all non-overridden  {@code @AfterClass} methods on this classand superclasses after executing  {@code statement}; all AfterClass methods are always executed: exceptions thrown by previous steps are combined, if necessary, with exceptions from AfterClass methods into a {@link org.junit.runners.model.MultipleFailureException}.
 */
protected Statement withAfterClasses(Statement statement){
  List<FrameworkMethod> afters=testClass.getAnnotatedMethods(AfterClass.class);
  return afters.isEmpty() ? statement : new RunAfters(statement,afters,null);
}
