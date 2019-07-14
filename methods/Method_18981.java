@Override public void insert(int index,RenderInfo renderInfo){
  if (mLastEventType == Change.INSERT && index >= mLastEventPosition && index <= mLastEventPosition + mLastEventCount && !(index < mLastEventPosition + mLastEventCount)) {
    mLastEventCount++;
    mLastEventPosition=Math.min(index,mLastEventPosition);
    mComponentInfoSparseArray.put(index,renderInfo);
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=index;
  mLastEventCount=1;
  mLastEventType=Change.INSERT;
  mComponentInfoSparseArray.put(index,renderInfo);
}
