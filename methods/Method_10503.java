private static Rect calculateTapArea(float x,float y,float coefficient,Camera.Size previewSize){
  float focusAreaSize=300;
  int areaSize=Float.valueOf(focusAreaSize * coefficient).intValue();
  int centerX=(int)(x / previewSize.width - 1000);
  int centerY=(int)(y / previewSize.height - 1000);
  int left=clamp(centerX - areaSize / 2,-1000,1000);
  int top=clamp(centerY - areaSize / 2,-1000,1000);
  RectF rectF=new RectF(left,top,left + areaSize,top + areaSize);
  return new Rect(Math.round(rectF.left),Math.round(rectF.top),Math.round(rectF.right),Math.round(rectF.bottom));
}
