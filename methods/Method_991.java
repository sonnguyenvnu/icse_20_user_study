private void disposeToBackground(Canvas canvas,AnimatedDrawableFrameInfo frameInfo){
  canvas.drawRect(frameInfo.xOffset,frameInfo.yOffset,frameInfo.xOffset + frameInfo.width,frameInfo.yOffset + frameInfo.height,mTransparentFillPaint);
}
