/** 
 * This indicates whether or not the drawer is completely hidden.
 * @return True if the drawer is hidden and not in the process of transitioning, otherwise false.
 */
public boolean isClosed(){
  if (hasMiniSize()) {
    return resizeTo == getMiniDrawerSize();
  }
  return translateTo == initTranslate.get() && !translateTimer.isRunning();
}
