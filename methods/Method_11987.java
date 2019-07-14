/** 
 * Invoke to tell listeners that a test suite is about to finish. Always invoke this method if you invoke  {@link #fireTestSuiteStarted(Description)}as listeners are likely to expect them to come in pairs.
 * @param description the description of the suite test (generally a class name)
 * @since 4.13
 */
public void fireTestSuiteFinished(final Description description){
  new SafeNotifier(){
    @Override protected void notifyListener(    RunListener each) throws Exception {
      each.testSuiteFinished(description);
    }
  }
.run();
}
