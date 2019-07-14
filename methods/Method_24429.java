public boolean isStopped(){
  if (animator != null) {
    return !animator.isAnimating();
  }
 else {
    return true;
  }
}
