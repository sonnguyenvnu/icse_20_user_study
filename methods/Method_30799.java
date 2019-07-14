private void updateLastMotion(MotionEvent event){
  mLastMotionY=getMotionEventY(event);
  ensureVelocityTracker().addMovement(event);
}
