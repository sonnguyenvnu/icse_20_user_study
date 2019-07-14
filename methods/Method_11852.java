@Override public Runner runnerForClass(Class<?> testClass) throws Throwable {
  if (isPre4Test(testClass)) {
    return new JUnit38ClassRunner(testClass);
  }
  return null;
}
