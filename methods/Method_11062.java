private void startLoading(long delay){
  if (mAnimatorSet != null && mAnimatorSet.isRunning()) {
    return;
  }
  this.removeCallbacks(mFreeFallRunnable);
  if (delay > 0) {
    this.postDelayed(mFreeFallRunnable,delay);
  }
 else {
    this.post(mFreeFallRunnable);
  }
}
