public Runner buildRunner(Class<? extends Runner> runnerClass,Class<?> testClass) throws Exception {
  try {
    return runnerClass.getConstructor(Class.class).newInstance(testClass);
  }
 catch (  NoSuchMethodException e) {
    try {
      return runnerClass.getConstructor(Class.class,RunnerBuilder.class).newInstance(testClass,suiteBuilder);
    }
 catch (    NoSuchMethodException e2) {
      String simpleName=runnerClass.getSimpleName();
      throw new InitializationError(String.format(CONSTRUCTOR_ERROR_FORMAT,simpleName,simpleName));
    }
  }
}
