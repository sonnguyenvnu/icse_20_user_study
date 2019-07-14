/** 
 * BatchingListUpdateCallback holds onto the last event to see if it can be merged with the next one. When stream of events finish, you should call this method to dispatch the last event.
 */
public void dispatchLastEvent(){
  if (mLastEventType == TYPE_NONE) {
    return;
  }
switch (mLastEventType) {
case TYPE_ADD:
    mWrapped.onInserted(mLastEventPosition,mLastEventCount);
  break;
case TYPE_REMOVE:
mWrapped.onRemoved(mLastEventPosition,mLastEventCount);
break;
case TYPE_CHANGE:
mWrapped.onChanged(mLastEventPosition,mLastEventCount,mLastEventPayload);
break;
}
mLastEventPayload=null;
mLastEventType=TYPE_NONE;
}
