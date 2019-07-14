/** 
 * Invoke to tell listeners that an atomic test flagged that it assumed something false.
 * @param failure the description of the test that failed and the{@link org.junit.AssumptionViolatedException} thrown
 */
public void fireTestAssumptionFailed(final Failure failure){
  new SafeNotifier(){
    @Override protected void notifyListener(    RunListener each) throws Exception {
      each.testAssumptionFailure(failure);
    }
  }
.run();
}
