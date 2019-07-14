/** 
 * Check if the spring was at rest in the prior iteration. This is used for ensuring the ending callbacks are fired as the spring comes to a rest.
 * @return true if the spring was at rest in the prior iteration
 */
public boolean wasAtRest(){
  return mWasAtRest;
}
