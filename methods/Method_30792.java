private void onPointerDown(MotionEvent event){
  int pointerIndex=MotionEventCompat.getActionIndex(event);
  mActivePointerId=MotionEventCompat.getPointerId(event,pointerIndex);
  mLastMotionY=MotionEventCompat.getY(event,pointerIndex);
}
