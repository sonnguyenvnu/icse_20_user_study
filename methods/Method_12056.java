/** 
 * Runs a  {@link Statement} that represents a leaf (aka atomic) test.
 */
protected final void runLeaf(Statement statement,Description description,RunNotifier notifier){
  EachTestNotifier eachNotifier=new EachTestNotifier(notifier,description);
  eachNotifier.fireTestStarted();
  try {
    statement.evaluate();
  }
 catch (  AssumptionViolatedException e) {
    eachNotifier.addFailedAssumption(e);
  }
catch (  Throwable e) {
    eachNotifier.addFailure(e);
  }
 finally {
    eachNotifier.fireTestFinished();
  }
}
