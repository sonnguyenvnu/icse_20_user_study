public ValueAnimator obtainAnimation(){
  if (animator == null) {
    animator=onCreateAnimation();
  }
  if (animator != null) {
    animator.addUpdateListener(this);
    animator.setStartDelay(animationDelay);
  }
  return animator;
}
