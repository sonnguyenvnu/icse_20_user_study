/** 
 * Temporary disables child clipping, the previous state could be restored by calling  {@link #restoreChildClipping()}. While clipping is disabled calling  {@link #setClipChildren(boolean)}would have no immediate effect, but the restored state would reflect the last set value
 */
void temporaryDisableChildClipping(){
  if (mClippingTemporaryDisabled) {
    return;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
    mClippingToRestore=getClipChildren();
  }
 else {
    mClippingToRestore=mClipChildren;
  }
  setClipChildren(false);
  mClippingTemporaryDisabled=true;
}
