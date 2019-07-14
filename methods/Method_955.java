private AnimatedDrawableBackendProvider getAnimatedDrawableBackendProvider(){
  if (mAnimatedDrawableBackendProvider == null) {
    mAnimatedDrawableBackendProvider=new AnimatedDrawableBackendProvider(){
      @Override public AnimatedDrawableBackend get(      AnimatedImageResult animatedImageResult,      Rect bounds){
        return new AnimatedDrawableBackendImpl(getAnimatedDrawableUtil(),animatedImageResult,bounds,mDownscaleFrameToDrawableDimensions);
      }
    }
;
  }
  return mAnimatedDrawableBackendProvider;
}
