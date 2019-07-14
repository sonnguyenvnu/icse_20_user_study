public boolean hasSuiteMethod(Class<?> testClass){
  try {
    testClass.getMethod("suite");
  }
 catch (  NoSuchMethodException e) {
    return false;
  }
  return true;
}
