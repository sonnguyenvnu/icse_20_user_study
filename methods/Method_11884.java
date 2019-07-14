private void testAborted(RunNotifier notifier,Description description,Throwable e){
  notifier.fireTestStarted(description);
  notifier.fireTestFailure(new Failure(description,e));
  notifier.fireTestFinished(description);
}
