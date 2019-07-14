private void fitTranslation(RectF contentRect,RectF boundsRect,PointF translation,float radians){
  float frameLeft=boundsRect.left;
  float frameTop=boundsRect.top;
  float frameRight=boundsRect.right;
  float frameBottom=boundsRect.bottom;
  if (contentRect.left > frameLeft) {
    frameRight+=contentRect.left - frameLeft;
    frameLeft=contentRect.left;
  }
  if (contentRect.top > frameTop) {
    frameBottom+=contentRect.top - frameTop;
    frameTop=contentRect.top;
  }
  if (contentRect.right < frameRight) {
    frameLeft+=contentRect.right - frameRight;
  }
  if (contentRect.bottom < frameBottom) {
    frameTop+=contentRect.bottom - frameBottom;
  }
  float deltaX=boundsRect.centerX() - (frameLeft + boundsRect.width() / 2.0f);
  float deltaY=boundsRect.centerY() - (frameTop + boundsRect.height() / 2.0f);
  float xCompX=(float)(Math.sin(Math.PI / 2 - radians) * deltaX);
  float xCompY=(float)(Math.cos(Math.PI / 2 - radians) * deltaX);
  float yCompX=(float)(Math.cos(Math.PI / 2 + radians) * deltaY);
  float yCompY=(float)(Math.sin(Math.PI / 2 + radians) * deltaY);
  translation.set(translation.x + xCompX + yCompX,translation.y + xCompY + yCompY);
}
