private void updateProgressLength(long deltaTimeInMillis){
  if (mPausedTimeWithoutGrowing >= PAUSE_GROWING_TIME) {
    mTimeStartGrowing+=deltaTimeInMillis;
    if (mTimeStartGrowing > BAR_SPIN_CYCLE_TIME) {
      mTimeStartGrowing-=BAR_SPIN_CYCLE_TIME;
      mPausedTimeWithoutGrowing=0;
      mBarGrowingFromFront=!mBarGrowingFromFront;
    }
    float distance=(float)Math.cos((mTimeStartGrowing / BAR_SPIN_CYCLE_TIME + 1) * Math.PI) / 2 + 0.5f;
    float length=BAR_MAX_LENGTH - mBarLength;
    if (mBarGrowingFromFront) {
      mBarExtraLength=distance * length;
    }
 else {
      float newLength=length * (1 - distance);
      mCurrentProgress+=(mBarExtraLength - newLength);
      mBarExtraLength=newLength;
    }
  }
 else {
    mPausedTimeWithoutGrowing+=deltaTimeInMillis;
  }
}
