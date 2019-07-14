/** 
 * Dispatches the motion event to the intercepting OnItemTouchListener or provides opportunity for OnItemTouchListeners to intercept.
 * @param e The MotionEvent
 * @return True if handled by an intercepting OnItemTouchListener.
 */
private boolean dispatchToOnItemTouchListeners(MotionEvent e){
  if (mInterceptingOnItemTouchListener == null) {
    if (e.getAction() == MotionEvent.ACTION_DOWN) {
      return false;
    }
    return findInterceptingOnItemTouchListener(e);
  }
 else {
    mInterceptingOnItemTouchListener.onTouchEvent(this,e);
    final int action=e.getAction();
    if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
      mInterceptingOnItemTouchListener=null;
    }
    return true;
  }
}
