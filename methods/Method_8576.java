private float fitScale(RectF contentRect,float scale,float ratio){
  float scaledW=contentRect.width() * ratio;
  float scaledH=contentRect.height() * ratio;
  float scaledX=(contentRect.width() - scaledW) / 2.0f;
  float scaledY=(contentRect.height() - scaledH) / 2.0f;
  contentRect.set(contentRect.left + scaledX,contentRect.top + scaledY,contentRect.left + scaledX + scaledW,contentRect.top + scaledY + scaledH);
  return scale * ratio;
}
