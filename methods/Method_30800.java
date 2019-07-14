private VelocityTracker ensureVelocityTracker(){
  if (mVelocityTracker == null) {
    mVelocityTracker=VelocityTracker.obtain();
  }
  return mVelocityTracker;
}
