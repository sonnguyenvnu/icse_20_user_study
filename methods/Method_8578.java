public float scaleWidthToMaxSize(RectF sizeRect,RectF maxSizeRect){
  float w=maxSizeRect.width();
  float h=(float)Math.floor(w * sizeRect.height() / sizeRect.width());
  if (h > maxSizeRect.height()) {
    h=maxSizeRect.height();
    w=(float)Math.floor(h * sizeRect.width() / sizeRect.height());
  }
  return w;
}
