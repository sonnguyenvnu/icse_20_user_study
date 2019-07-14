@Override public AnimatedDrawableBackend forNewBounds(Rect bounds){
  Rect boundsToUse=getBoundsToUse(mAnimatedImage,bounds);
  if (boundsToUse.equals(mRenderedBounds)) {
    return this;
  }
  return new AnimatedDrawableBackendImpl(mAnimatedDrawableUtil,mAnimatedImageResult,bounds,mDownscaleFrameToDrawableDimensions);
}
