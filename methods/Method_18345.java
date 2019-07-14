@Override @TargetApi(LOLLIPOP) public boolean onTouchEvent(MotionEvent event,View host){
  final Rect bounds=getBounds();
  final int x=(int)event.getX() - bounds.left;
  final int y=(int)event.getY() - bounds.top;
  mDrawable.setHotspot(x,y);
  return false;
}
