/** 
 * This indicates whether or not the drawer is completely shown.
 * @return True if the drawer is totally visible and not transitioning, otherwise false.
 */
public boolean isOpened(){
  if (hasMiniSize()) {
    return resizeTo == getDefaultDrawerSize() || prefSizeProperty.get() >= getDefaultDrawerSize();
  }
  return (translateTo == 0 || translateProperty.get() == 0) && !translateTimer.isRunning();
}
