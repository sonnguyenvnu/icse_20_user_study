/** 
 * Attaches listeners (if specified) to the given controller. 
 */
protected void maybeAttachListeners(AbstractDraweeController controller){
  if (mBoundControllerListeners != null) {
    for (    ControllerListener<? super INFO> listener : mBoundControllerListeners) {
      controller.addControllerListener(listener);
    }
  }
  if (mControllerListener != null) {
    controller.addControllerListener(mControllerListener);
  }
  if (mAutoPlayAnimations) {
    controller.addControllerListener(sAutoPlayAnimationsListener);
  }
}
