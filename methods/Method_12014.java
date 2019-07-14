/** 
 * Returns a Statement that, when executed, either returns normally if {@code method} passes, or throws an exception if {@code method} fails.Here is an outline of the default implementation: <ul> <li>Invoke  {@code method} on the result of {@link #createTest(org.junit.runners.model.FrameworkMethod)}, and throw any exceptions thrown by either operation. <li>HOWEVER, if  {@code method}'s  {@code @Test} annotation has the {@link Test#expected()}attribute, return normally only if the previous step threw an exception of the correct type, and throw an exception otherwise. <li>HOWEVER, if  {@code method}'s  {@code @Test} annotation has the {@code timeout} attribute, throw an exception if the previous step takes morethan the specified number of milliseconds. <li>ALWAYS run all non-overridden  {@code @Before} methods on this classand superclasses before any of the previous steps; if any throws an Exception, stop execution and pass the exception on. <li>ALWAYS run all non-overridden  {@code @After} methods on this classand superclasses after any of the previous steps; all After methods are always executed: exceptions thrown by previous steps are combined, if necessary, with exceptions from After methods into a {@link MultipleFailureException}. <li>ALWAYS allow  {@code @Rule} fields to modify the execution of theabove steps. A  {@code Rule} may prevent all execution of the above steps,or add additional behavior before and after, or modify thrown exceptions. For more information, see  {@link TestRule}</ul> This can be overridden in subclasses, either by overriding this method, or the implementations creating each sub-statement.
 */
protected Statement methodBlock(final FrameworkMethod method){
  Object test;
  try {
    test=new ReflectiveCallable(){
      @Override protected Object runReflectiveCall() throws Throwable {
        return createTest(method);
      }
    }
.run();
  }
 catch (  Throwable e) {
    return new Fail(e);
  }
  Statement statement=methodInvoker(method,test);
  statement=possiblyExpectingExceptions(method,test,statement);
  statement=withPotentialTimeout(method,test,statement);
  statement=withBefores(method,test,statement);
  statement=withAfters(method,test,statement);
  statement=withRules(method,test,statement);
  statement=withInterruptIsolation(statement);
  return statement;
}
