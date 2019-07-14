private void constrainRectByHeight(RectF rect,float aspectRatio){
  float h=rect.height();
  float w=h * aspectRatio;
  rect.right=rect.left + w;
  rect.bottom=rect.top + h;
}
