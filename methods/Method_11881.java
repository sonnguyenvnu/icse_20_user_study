protected void runMethods(final RunNotifier notifier){
  for (  Method method : testMethods) {
    invokeTestMethod(method,notifier);
  }
}
