@Override public boolean shouldHandleTouchEvent(MotionEvent event){
  return Build.VERSION.SDK_INT >= LOLLIPOP && mDrawable != null && mDrawable instanceof RippleDrawable && event.getActionMasked() == MotionEvent.ACTION_DOWN && getBounds().contains((int)event.getX(),(int)event.getY());
}
