@Override public void delete(int index){
  if (mLastEventType == Change.DELETE && mLastEventPosition >= index && mLastEventPosition <= index + 1) {
    mLastEventCount++;
    mLastEventPosition=index;
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=index;
  mLastEventCount=1;
  mLastEventType=Change.DELETE;
}
