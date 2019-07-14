@Override public void onRemoved(int position,int count){
  if (mLastEventType == TYPE_REMOVE && mLastEventPosition >= position && mLastEventPosition <= position + count) {
    mLastEventCount+=count;
    mLastEventPosition=position;
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=position;
  mLastEventCount=count;
  mLastEventType=TYPE_REMOVE;
}
