private boolean isFullFrame(AnimatedDrawableFrameInfo frameInfo){
  return frameInfo.xOffset == 0 && frameInfo.yOffset == 0 && frameInfo.width == mAnimatedDrawableBackend.getRenderedWidth() && frameInfo.height == mAnimatedDrawableBackend.getRenderedHeight();
}
