@Override public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
  distanceY=e2.getRawY() - e1.getRawY();
  if (Math.abs(distanceY) < maxDistanceY) {
    if (distanceY > minDistanceY) {
      scrollPage(true);
      return true;
    }
    if (distanceY < -minDistanceY) {
      scrollPage(false);
      return true;
    }
  }
  return false;
}
