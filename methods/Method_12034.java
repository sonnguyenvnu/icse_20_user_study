/** 
 * Always returns a runner for the given test class. <p>In case of an exception a runner will be returned that prints an error instead of running tests. <p>Note that some of the internal JUnit implementations of RunnerBuilder will return {@code null} from this method, but no RunnerBuilder passed to a Runner constructor willreturn  {@code null} from this method.
 * @param testClass class to be run
 * @return a Runner
 */
public Runner safeRunnerForClass(Class<?> testClass){
  try {
    Runner runner=runnerForClass(testClass);
    if (runner != null) {
      configureRunner(runner);
    }
    return runner;
  }
 catch (  Throwable e) {
    return new ErrorReportingRunner(testClass,e);
  }
}
