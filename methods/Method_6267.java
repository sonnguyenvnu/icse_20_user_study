private Rect calculateTapArea(float x,float y,float coefficient){
  int areaSize=Float.valueOf(focusAreaSize * coefficient).intValue();
  int left=clamp((int)x - areaSize / 2,0,getWidth() - areaSize);
  int top=clamp((int)y - areaSize / 2,0,getHeight() - areaSize);
  RectF rectF=new RectF(left,top,left + areaSize,top + areaSize);
  matrix.mapRect(rectF);
  return new Rect(Math.round(rectF.left),Math.round(rectF.top),Math.round(rectF.right),Math.round(rectF.bottom));
}
