/** 
 * Returns a  {@link Statement}: run all non-overridden  {@code @BeforeClass} methods on this classand superclasses before executing  {@code statement}; if any throws an Exception, stop execution and pass the exception on.
 */
protected Statement withBeforeClasses(Statement statement){
  List<FrameworkMethod> befores=testClass.getAnnotatedMethods(BeforeClass.class);
  return befores.isEmpty() ? statement : new RunBefores(statement,befores,null);
}
