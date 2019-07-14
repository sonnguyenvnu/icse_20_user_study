/** 
 * Invoke to tell listeners that an atomic test failed.
 * @param failure the description of the test that failed and the exception thrown
 */
public void fireTestFailure(Failure failure){
  fireTestFailures(listeners,asList(failure));
}
