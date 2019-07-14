public void resetAnimator(){
  if (animator != null) {
    animator.cancel();
    animator=null;
  }
}
