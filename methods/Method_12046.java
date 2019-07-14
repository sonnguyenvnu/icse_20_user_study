/** 
 * @deprecated Please use {@link #ParentRunner(org.junit.runners.model.TestClass)}.
 * @since 4.12
 */
@Deprecated protected TestClass createTestClass(Class<?> testClass){
  return new TestClass(testClass);
}
