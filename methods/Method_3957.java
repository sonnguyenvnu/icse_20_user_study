/** 
 * Looks for an OnItemTouchListener that wants to intercept. <p>Calls  {@link OnItemTouchListener#onInterceptTouchEvent(RecyclerView,MotionEvent)} on eachof the registered  {@link OnItemTouchListener}s, passing in the MotionEvent. If one returns true and the action is not ACTION_CANCEL, saves the intercepting OnItemTouchListener to be called for future  {@link RecyclerView#onTouchEvent(MotionEvent)}and immediately returns true. If none want to intercept or the action is ACTION_CANCEL, returns false.
 * @param e The MotionEvent
 * @return true if an OnItemTouchListener is saved as intercepting.
 */
private boolean findInterceptingOnItemTouchListener(MotionEvent e){
  int action=e.getAction();
  final int listenerCount=mOnItemTouchListeners.size();
  for (int i=0; i < listenerCount; i++) {
    final OnItemTouchListener listener=mOnItemTouchListeners.get(i);
    if (listener.onInterceptTouchEvent(this,e) && action != MotionEvent.ACTION_CANCEL) {
      mInterceptingOnItemTouchListener=listener;
      return true;
    }
  }
  return false;
}
