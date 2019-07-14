/** 
 * Installs a retry manager (if specified) to the given controller. 
 */
protected void maybeBuildAndSetRetryManager(AbstractDraweeController controller){
  if (!mTapToRetryEnabled) {
    return;
  }
  controller.getRetryManager().setTapToRetryEnabled(mTapToRetryEnabled);
  maybeBuildAndSetGestureDetector(controller);
}
