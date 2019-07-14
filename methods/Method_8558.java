private void constrainRectByWidth(RectF rect,float aspectRatio){
  float w=rect.width();
  float h=w / aspectRatio;
  rect.right=rect.left + w;
  rect.bottom=rect.top + h;
}
