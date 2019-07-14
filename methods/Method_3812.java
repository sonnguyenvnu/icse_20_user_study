private int swipeIfNecessary(ViewHolder viewHolder){
  if (mActionState == ACTION_STATE_DRAG) {
    return 0;
  }
  final int originalMovementFlags=mCallback.getMovementFlags(mRecyclerView,viewHolder);
  final int absoluteMovementFlags=mCallback.convertToAbsoluteDirection(originalMovementFlags,ViewCompat.getLayoutDirection(mRecyclerView));
  final int flags=(absoluteMovementFlags & ACTION_MODE_SWIPE_MASK) >> (ACTION_STATE_SWIPE * DIRECTION_FLAG_COUNT);
  if (flags == 0) {
    return 0;
  }
  final int originalFlags=(originalMovementFlags & ACTION_MODE_SWIPE_MASK) >> (ACTION_STATE_SWIPE * DIRECTION_FLAG_COUNT);
  int swipeDir;
  if (Math.abs(mDx) > Math.abs(mDy)) {
    if ((swipeDir=checkHorizontalSwipe(viewHolder,flags)) > 0) {
      if ((originalFlags & swipeDir) == 0) {
        return Callback.convertToRelativeDirection(swipeDir,ViewCompat.getLayoutDirection(mRecyclerView));
      }
      return swipeDir;
    }
    if ((swipeDir=checkVerticalSwipe(viewHolder,flags)) > 0) {
      return swipeDir;
    }
  }
 else {
    if ((swipeDir=checkVerticalSwipe(viewHolder,flags)) > 0) {
      return swipeDir;
    }
    if ((swipeDir=checkHorizontalSwipe(viewHolder,flags)) > 0) {
      if ((originalFlags & swipeDir) == 0) {
        return Callback.convertToRelativeDirection(swipeDir,ViewCompat.getLayoutDirection(mRecyclerView));
      }
      return swipeDir;
    }
  }
  return 0;
}
