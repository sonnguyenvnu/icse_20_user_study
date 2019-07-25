public void stop(){
  if (animator != null) {
    animator.cancel();
    animator=null;
  }
}
