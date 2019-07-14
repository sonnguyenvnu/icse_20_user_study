private RectF calculateBounds(){
  int availableWidth=getWidth() - getPaddingLeft() - getPaddingRight();
  int availableHeight=getHeight() - getPaddingTop() - getPaddingBottom();
  int sideLength=Math.min(availableWidth,availableHeight);
  float left=getPaddingLeft() + (availableWidth - sideLength) / 2f;
  float top=getPaddingTop() + (availableHeight - sideLength) / 2f;
  return new RectF(left,top,left + sideLength,top + sideLength);
}
