private void checkAnimation(){
  if (animateToProgress != animProgress) {
    long newTime=SystemClock.elapsedRealtime();
    long dt=newTime - lastUpdateTime;
    lastUpdateTime=newTime;
    if (animProgress < animateToProgress) {
      animProgress+=dt / 180.0f;
      if (animProgress > animateToProgress) {
        animProgress=animateToProgress;
      }
    }
 else {
      animProgress-=dt / 180.0f;
      if (animProgress < animateToProgress) {
        animProgress=animateToProgress;
      }
    }
    updatePath();
    invalidateSelf();
  }
}
