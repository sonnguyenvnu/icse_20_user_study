public void setActualRect(RectF rect){
  actualRect.set(rect);
  updateTouchAreas();
  invalidate();
}
