/** 
 * Restores child clipping to the state it was in when  {@link #temporaryDisableChildClipping()}was called, unless there were attempts to set a new value, while the clipping was disabled, then would be restored to the last set value
 */
void restoreChildClipping(){
  if (!mClippingTemporaryDisabled) {
    return;
  }
  mClippingTemporaryDisabled=false;
  setClipChildren(mClippingToRestore);
}
