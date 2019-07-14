public Statement apply(Statement base,org.junit.runner.Description description){
  return new ExpectedExceptionStatement(base);
}
