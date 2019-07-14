public int checkHorizontalSwipe(ViewHolder viewHolder,int flags){
  if ((flags & (LEFT | RIGHT)) != 0) {
    final int dirFlag=mDx > 0 ? RIGHT : LEFT;
    if (mVelocityTracker != null && mActivePointerId > -1) {
      mVelocityTracker.computeCurrentVelocity(PIXELS_PER_SECOND,mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
      final float xVelocity=mVelocityTracker.getXVelocity(mActivePointerId);
      final float yVelocity=mVelocityTracker.getYVelocity(mActivePointerId);
      final int velDirFlag=xVelocity > 0f ? RIGHT : LEFT;
      final float absXVelocity=Math.abs(xVelocity);
      if ((velDirFlag & flags) != 0 && dirFlag == velDirFlag && absXVelocity >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity) && absXVelocity > Math.abs(yVelocity)) {
        return velDirFlag;
      }
    }
    final float threshold=mRecyclerView.getWidth() * mCallback.getSwipeThreshold(viewHolder);
    if ((flags & dirFlag) != 0 && Math.abs(mDx) > threshold) {
      return dirFlag;
    }
  }
  return 0;
}
