@Override public Runner runnerForClass(Class<?> testClass) throws Throwable {
  List<RunnerBuilder> builders=Arrays.asList(ignoredBuilder(),annotatedBuilder(),suiteMethodBuilder(),junit3Builder(),junit4Builder());
  for (  RunnerBuilder each : builders) {
    Runner runner=each.safeRunnerForClass(testClass);
    if (runner != null) {
      return runner;
    }
  }
  return null;
}
