private BaseViewAnimator play(){
  animator.setTarget(target);
  if (pivotX == YoYo.CENTER_PIVOT) {
    ViewCompat.setPivotX(target,target.getMeasuredWidth() / 2.0f);
  }
 else {
    target.setPivotX(pivotX);
  }
  if (pivotY == YoYo.CENTER_PIVOT) {
    ViewCompat.setPivotY(target,target.getMeasuredHeight() / 2.0f);
  }
 else {
    target.setPivotY(pivotY);
  }
  animator.setDuration(duration).setRepeatTimes(repeatTimes).setRepeatMode(repeatMode).setInterpolator(interpolator).setStartDelay(delay);
  if (callbacks.size() > 0) {
    for (    Animator.AnimatorListener callback : callbacks) {
      animator.addAnimatorListener(callback);
    }
  }
  animator.animate();
  return animator;
}
