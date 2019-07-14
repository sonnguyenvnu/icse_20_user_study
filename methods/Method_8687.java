@Override public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
  scroll.abortAnimation();
  if (currentPhotos.size() >= 10) {
    scroll.fling(drawDx,0,Math.round(velocityX),0,getMinScrollX(),getMaxScrollX(),0,0);
  }
  return false;
}
