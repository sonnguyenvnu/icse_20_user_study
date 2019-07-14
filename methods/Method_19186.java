void setClippingColor(int clippingColor){
  if (mCornerPaint.getColor() == clippingColor) {
    return;
  }
  mCornerPaint.setColor(clippingColor);
  mDirty=true;
  invalidateSelf();
}
