@Override protected Runner createRunner(){
  Runner runner=request.getRunner();
  try {
    ordering.apply(runner);
  }
 catch (  InvalidOrderingException e) {
    return new ErrorReportingRunner(ordering.getClass(),e);
  }
  return runner;
}
