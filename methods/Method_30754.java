@Override protected void onResize(float width,float height){
  mPath.rewind();
  float radius=Math.min(width,height) / 2;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    mPath.addRoundRect(0,0,width,height,radius,radius,Path.Direction.CW);
  }
 else {
    mTempRectForCanvasAddRoundRect.set(0,0,width,height);
    mPath.addRoundRect(mTempRectForCanvasAddRoundRect,radius,radius,Path.Direction.CW);
  }
}
