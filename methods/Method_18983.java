@Override public void update(int index,RenderInfo renderInfo){
  if (mLastEventType == Change.UPDATE && !(index > mLastEventPosition + mLastEventCount || index + 1 < mLastEventPosition)) {
    int previousEnd=mLastEventPosition + mLastEventCount;
    mLastEventPosition=Math.min(index,mLastEventPosition);
    mLastEventCount=Math.max(previousEnd,index + 1) - mLastEventPosition;
    mComponentInfoSparseArray.put(index,renderInfo);
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=index;
  mLastEventCount=1;
  mLastEventType=Change.UPDATE;
  mComponentInfoSparseArray.put(index,renderInfo);
}
