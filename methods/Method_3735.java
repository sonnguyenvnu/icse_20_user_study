@Override public void onChanged(int position,int count,Object payload){
  if (mLastEventType == TYPE_CHANGE && !(position > mLastEventPosition + mLastEventCount || position + count < mLastEventPosition || mLastEventPayload != payload)) {
    int previousEnd=mLastEventPosition + mLastEventCount;
    mLastEventPosition=Math.min(position,mLastEventPosition);
    mLastEventCount=Math.max(previousEnd,position + count) - mLastEventPosition;
    return;
  }
  dispatchLastEvent();
  mLastEventPosition=position;
  mLastEventCount=count;
  mLastEventPayload=payload;
  mLastEventType=TYPE_CHANGE;
}
