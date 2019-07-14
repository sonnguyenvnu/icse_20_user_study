private static boolean isWithinBounds(Rect bounds,MotionEvent event){
  return bounds.contains((int)event.getX(),(int)event.getY());
}
