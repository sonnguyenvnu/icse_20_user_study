@Override public void stop(){
  if (RxAnimationTool.isStarted(animator)) {
    animator.removeAllUpdateListeners();
    animator.end();
    reset();
  }
}
