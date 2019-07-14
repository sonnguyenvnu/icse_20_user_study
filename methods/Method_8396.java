@Keep public void setAnimationProgress(float progress){
  animProgress=progress;
  animateToProgress=progress;
  updatePath();
  invalidateSelf();
}
