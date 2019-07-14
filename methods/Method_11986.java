/** 
 * Invoke to tell listeners that a test suite is about to start. Runners are strongly encouraged--but not required--to call this method. If this method is called for a given  {@link Description} then {@link #fireTestSuiteFinished(Description)} MUSTbe called for the same  {@code Description}.
 * @param description the description of the suite test (generally a class name)
 * @since 4.13
 */
public void fireTestSuiteStarted(final Description description){
  new SafeNotifier(){
    @Override protected void notifyListener(    RunListener each) throws Exception {
      each.testSuiteStarted(description);
    }
  }
.run();
}
