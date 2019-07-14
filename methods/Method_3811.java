@SuppressWarnings("WeakerAccess") void updateDxDy(MotionEvent ev,int directionFlags,int pointerIndex){
  final float x=ev.getX(pointerIndex);
  final float y=ev.getY(pointerIndex);
  mDx=x - mInitialTouchX;
  mDy=y - mInitialTouchY;
  if ((directionFlags & LEFT) == 0) {
    mDx=Math.max(0,mDx);
  }
  if ((directionFlags & RIGHT) == 0) {
    mDx=Math.min(0,mDx);
  }
  if ((directionFlags & UP) == 0) {
    mDy=Math.max(0,mDy);
  }
  if ((directionFlags & DOWN) == 0) {
    mDy=Math.min(0,mDy);
  }
}
