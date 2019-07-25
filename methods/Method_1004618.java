private boolean contains(final MotionEvent pEvent){
  final Rect canvasRect=getCanvasRect();
  return canvasRect != null && canvasRect.contains((int)pEvent.getX(),(int)pEvent.getY());
}
