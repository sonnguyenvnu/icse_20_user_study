/** 
 * Similar to  {@link #setText(String)} but provides the optional argument of whether toanimate to the provided text or not.
 * @param text    the text to display.
 * @param animate whether to animate to text.
 */
public synchronized void setText(String text,boolean animate){
  final char[] targetText=text == null ? new char[0] : text.toCharArray();
  if (columnManager.shouldDebounceText(targetText)) {
    return;
  }
  columnManager.setText(targetText);
  setContentDescription(text);
  if (animate) {
    if (animator.isRunning()) {
      animator.cancel();
    }
    animator.setDuration(animationDurationInMillis);
    animator.setInterpolator(animationInterpolator);
    animator.start();
  }
 else {
    columnManager.setAnimationProgress(1f);
    columnManager.onAnimationEnd();
    checkForRelayout();
    invalidate();
  }
}
