boolean isPre4Test(Class<?> testClass){
  return junit.framework.TestCase.class.isAssignableFrom(testClass);
}
