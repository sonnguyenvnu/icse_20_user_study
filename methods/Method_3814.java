private int checkVerticalSwipe(ViewHolder viewHolder,int flags){
  if ((flags & (UP | DOWN)) != 0) {
    final int dirFlag=mDy > 0 ? DOWN : UP;
    if (mVelocityTracker != null && mActivePointerId > -1) {
      mVelocityTracker.computeCurrentVelocity(PIXELS_PER_SECOND,mCallback.getSwipeVelocityThreshold(mMaxSwipeVelocity));
      final float xVelocity=mVelocityTracker.getXVelocity(mActivePointerId);
      final float yVelocity=mVelocityTracker.getYVelocity(mActivePointerId);
      final int velDirFlag=yVelocity > 0f ? DOWN : UP;
      final float absYVelocity=Math.abs(yVelocity);
      if ((velDirFlag & flags) != 0 && velDirFlag == dirFlag && absYVelocity >= mCallback.getSwipeEscapeVelocity(mSwipeEscapeVelocity) && absYVelocity > Math.abs(xVelocity)) {
        return velDirFlag;
      }
    }
    final float threshold=mRecyclerView.getHeight() * mCallback.getSwipeThreshold(viewHolder);
    if ((flags & dirFlag) != 0 && Math.abs(mDy) > threshold) {
      return dirFlag;
    }
  }
  return 0;
}
