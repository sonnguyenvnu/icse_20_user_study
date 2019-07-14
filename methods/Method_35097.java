void performAnimation(@NonNull final ViewGroup container,@Nullable final View from,@Nullable final View to,final boolean isPush,final boolean toAddedToContainer,@NonNull final ControllerChangeCompletedListener changeListener){
  if (canceled) {
    complete(changeListener,null);
    return;
  }
  if (needsImmediateCompletion) {
    if (from != null && (!isPush || removesFromViewOnPush)) {
      container.removeView(from);
    }
    complete(changeListener,null);
    if (isPush && from != null) {
      resetFromView(from);
    }
    return;
  }
  animator=getAnimator(container,from,to,isPush,toAddedToContainer);
  if (animationDuration > 0) {
    animator.setDuration(animationDuration);
  }
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationCancel(    Animator animation){
      if (from != null) {
        resetFromView(from);
      }
      if (to != null && to.getParent() == container) {
        container.removeView(to);
      }
      complete(changeListener,this);
    }
    @Override public void onAnimationEnd(    Animator animation){
      if (!canceled && animator != null) {
        if (from != null && (!isPush || removesFromViewOnPush)) {
          container.removeView(from);
        }
        complete(changeListener,this);
        if (isPush && from != null) {
          resetFromView(from);
        }
      }
    }
  }
);
  animator.start();
}
