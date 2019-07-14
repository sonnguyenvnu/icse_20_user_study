private List<CloseableReference<Bitmap>> decodeAllFrames(AnimatedImage image,Bitmap.Config bitmapConfig){
  AnimatedImageResult tempResult=AnimatedImageResult.forAnimatedImage(image);
  AnimatedDrawableBackend drawableBackend=mAnimatedDrawableBackendProvider.get(tempResult,null);
  final List<CloseableReference<Bitmap>> bitmaps=new ArrayList<>(drawableBackend.getFrameCount());
  AnimatedImageCompositor animatedImageCompositor=new AnimatedImageCompositor(drawableBackend,new AnimatedImageCompositor.Callback(){
    @Override public void onIntermediateResult(    int frameNumber,    Bitmap bitmap){
    }
    @Override public CloseableReference<Bitmap> getCachedBitmap(    int frameNumber){
      return CloseableReference.cloneOrNull(bitmaps.get(frameNumber));
    }
  }
);
  for (int i=0; i < drawableBackend.getFrameCount(); i++) {
    CloseableReference<Bitmap> bitmap=createBitmap(drawableBackend.getWidth(),drawableBackend.getHeight(),bitmapConfig);
    animatedImageCompositor.renderFrame(i,bitmap.get());
    bitmaps.add(bitmap);
  }
  return bitmaps;
}
