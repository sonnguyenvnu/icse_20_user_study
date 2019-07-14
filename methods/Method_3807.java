@SuppressWarnings("WeakerAccess") void obtainVelocityTracker(){
  if (mVelocityTracker != null) {
    mVelocityTracker.recycle();
  }
  mVelocityTracker=VelocityTracker.obtain();
}
