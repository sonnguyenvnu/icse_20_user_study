private void updateActivePointerId(MotionEvent event){
  mActivePointerId=MotionEventCompat.getPointerId(event,0);
}
