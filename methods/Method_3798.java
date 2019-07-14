private void stopGestureDetection(){
  if (mItemTouchHelperGestureListener != null) {
    mItemTouchHelperGestureListener.doNotReactToLongPress();
    mItemTouchHelperGestureListener=null;
  }
  if (mGestureDetector != null) {
    mGestureDetector=null;
  }
}
