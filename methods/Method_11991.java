/** 
 * Invoke to tell listeners that an atomic test was ignored.
 * @param description the description of the ignored test
 */
public void fireTestIgnored(final Description description){
  new SafeNotifier(){
    @Override protected void notifyListener(    RunListener each) throws Exception {
      each.testIgnored(description);
    }
  }
.run();
}
