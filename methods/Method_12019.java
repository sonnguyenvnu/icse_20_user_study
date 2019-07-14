/** 
 * Returns a  {@link Statement}: run all non-overridden  {@code @After}methods on this class and superclasses before running  {@code next}; all After methods are always executed: exceptions thrown by previous steps are combined, if necessary, with exceptions from After methods into a {@link MultipleFailureException}.
 */
protected Statement withAfters(FrameworkMethod method,Object target,Statement statement){
  List<FrameworkMethod> afters=getTestClass().getAnnotatedMethods(After.class);
  return afters.isEmpty() ? statement : new RunAfters(statement,afters,target);
}
