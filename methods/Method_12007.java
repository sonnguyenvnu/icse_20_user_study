/** 
 * Returns the methods that run tests. Default implementation returns all methods annotated with  {@code @Test} on this class and superclasses thatare not overridden.
 */
protected List<FrameworkMethod> computeTestMethods(){
  return getTestClass().getAnnotatedMethods(Test.class);
}
