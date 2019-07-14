@Override public Object createTest() throws Exception {
  InjectionType injectionType=getInjectionType();
switch (injectionType) {
case CONSTRUCTOR:
    return createTestUsingConstructorInjection();
case FIELD:
  return createTestUsingFieldInjection();
default :
throw new IllegalStateException("The injection type " + injectionType + " is not supported.");
}
}
