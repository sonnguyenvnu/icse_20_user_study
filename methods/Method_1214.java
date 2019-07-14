/** 
 * Returns whether currently set controller is valid: not null and attached to the hierarchy that is held by the holder
 */
public boolean isControllerValid(){
  return mController != null && mController.getHierarchy() == mHierarchy;
}
