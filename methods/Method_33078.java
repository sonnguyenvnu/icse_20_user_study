/** 
 * This indicates whether or not the drawer is in the process of being hidden.
 * @return True if the drawer is transitioning from open to closed, otherwise false.
 */
public boolean isClosing(){
  boolean condition=hasMiniSize() ? resizeTo == getMiniDrawerSize() : translateTo == initTranslate.get();
  return condition && translateTimer.isRunning();
}
