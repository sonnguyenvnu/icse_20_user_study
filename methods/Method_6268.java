public void focusToPoint(int x,int y){
  Rect focusRect=calculateTapArea(x,y,1f);
  Rect meteringRect=calculateTapArea(x,y,1.5f);
  if (cameraSession != null) {
    cameraSession.focusToRect(focusRect,meteringRect);
  }
  focusProgress=0.0f;
  innerAlpha=1.0f;
  outerAlpha=1.0f;
  cx=x;
  cy=y;
  lastDrawTime=System.currentTimeMillis();
  invalidate();
}
