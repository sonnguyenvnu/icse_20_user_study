private AnimatedImageFactory buildAnimatedImageFactory(){
  AnimatedDrawableBackendProvider animatedDrawableBackendProvider=new AnimatedDrawableBackendProvider(){
    @Override public AnimatedDrawableBackend get(    AnimatedImageResult imageResult,    Rect bounds){
      return new AnimatedDrawableBackendImpl(getAnimatedDrawableUtil(),imageResult,bounds,mDownscaleFrameToDrawableDimensions);
    }
  }
;
  return new AnimatedImageFactoryImpl(animatedDrawableBackendProvider,mPlatformBitmapFactory);
}
