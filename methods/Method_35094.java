@Override public void completeImmediately(){
  super.completeImmediately();
  needsImmediateCompletion=true;
  if (animator != null) {
    animator.end();
  }
 else   if (onAnimationReadyOrAbortedListener != null) {
    onAnimationReadyOrAbortedListener.onReadyOrAborted();
  }
}
