private Object createTestUsingConstructorInjection() throws Exception {
  return getTestClass().getOnlyConstructor().newInstance(parameters);
}
