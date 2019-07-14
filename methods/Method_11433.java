/** 
 * Fade the selector spinnerwheel via an animation.
 * @param animationDuration The duration of the animation.
 */
private void fadeSelectorWheel(long animationDuration){
  mDimSelectorWheelAnimator.setDuration(animationDuration);
  mDimSelectorWheelAnimator.start();
}
