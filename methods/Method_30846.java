@Override protected boolean onLevelChange(int level){
  super.onLevelChange(level);
  float value=level / (float)MAX_LEVEL;
  mState.mCurrentDegrees=MathUtils.lerp(mState.mFromDegrees,mState.mToDegrees,value);
  invalidateSelf();
  return true;
}
