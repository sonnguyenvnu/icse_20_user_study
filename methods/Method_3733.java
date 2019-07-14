@Override public void onInserted(int position,int count){
  if (mLastEventType == TYPE_ADD && position >= mLastEventPosition && position <= mLastEventPosition + mLastEventCount) {
    mLastEventCount+=count;
    mLastEventPosition=Math.min(position,mLastEventPosition);
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=position;
  mLastEventCount=count;
  mLastEventType=TYPE_ADD;
}
