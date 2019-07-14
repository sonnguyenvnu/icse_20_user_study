@Override public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
  if (onBottomDragListener != null && e1.getRawY() > ScreenUtil.getScreenSize(this)[1] - ((int)getResources().getDimension(R.dimen.bottom_drag_height))) {
    float maxDragHeight=getResources().getDimension(R.dimen.bottom_drag_max_height);
    float distanceY=e2.getRawY() - e1.getRawY();
    if (distanceY < maxDragHeight && distanceY > -maxDragHeight) {
      float minDragWidth=getResources().getDimension(R.dimen.bottom_drag_min_width);
      float distanceX=e2.getRawX() - e1.getRawX();
      if (distanceX > minDragWidth) {
        onBottomDragListener.onDragBottom(false);
        return true;
      }
 else       if (distanceX < -minDragWidth) {
        onBottomDragListener.onDragBottom(true);
        return true;
      }
    }
  }
  return false;
}
