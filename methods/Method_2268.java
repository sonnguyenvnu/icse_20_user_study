private void ensureBorderPaint(){
  if (mBorderOptions != null) {
    mBorderPaint.setStrokeWidth(mBorderOptions.width);
    mBorderPaint.setColor(DrawableUtils.multiplyColorAlpha(mBorderOptions.color,mAlpha));
  }
}
