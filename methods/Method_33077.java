/** 
 * This indicates whether or not the drawer is in the process of being shown.
 * @return True if the drawer is transitioning from closed to open, otherwise false.
 */
public boolean isOpening(){
  boolean condition=hasMiniSize() ? resizeTo == getDefaultDrawerSize() : translateTo == 0;
  return condition && translateTimer.isRunning();
}
