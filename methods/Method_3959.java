private void onPointerUp(MotionEvent e){
  final int actionIndex=e.getActionIndex();
  if (e.getPointerId(actionIndex) == mScrollPointerId) {
    final int newIndex=actionIndex == 0 ? 1 : 0;
    mScrollPointerId=e.getPointerId(newIndex);
    mInitialTouchX=mLastTouchX=(int)(e.getX(newIndex) + 0.5f);
    mInitialTouchY=mLastTouchY=(int)(e.getY(newIndex) + 0.5f);
  }
}
