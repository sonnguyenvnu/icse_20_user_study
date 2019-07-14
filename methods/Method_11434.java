/** 
 * Fade the selector spinnerwheel via an animation.
 * @param animationDuration The duration of the animation.
 */
private void lightSeparators(long animationDuration){
  mDimSeparatorsAnimator.setDuration(animationDuration);
  mDimSeparatorsAnimator.start();
}
