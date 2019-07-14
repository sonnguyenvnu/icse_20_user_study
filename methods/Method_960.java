private AnimatedDrawableBackend createAnimatedDrawableBackend(AnimatedImageResult animatedImageResult){
  AnimatedImage animatedImage=animatedImageResult.getImage();
  Rect initialBounds=new Rect(0,0,animatedImage.getWidth(),animatedImage.getHeight());
  return mAnimatedDrawableBackendProvider.get(animatedImageResult,initialBounds);
}
