private Runner buildRunner(Description each){
  if (each.toString().equals("TestSuite with 0 tests")) {
    return Suite.emptySuite();
  }
  if (each.toString().startsWith(MALFORMED_JUNIT_3_TEST_CLASS_PREFIX)) {
    return new JUnit38ClassRunner(new TestSuite(getMalformedTestClass(each)));
  }
  Class<?> type=each.getTestClass();
  if (type == null) {
    throw new RuntimeException("Can't build a runner from description [" + each + "]");
  }
  String methodName=each.getMethodName();
  if (methodName == null) {
    return Request.aClass(type).getRunner();
  }
  return Request.method(type,methodName).getRunner();
}
