private CloseableReference<Bitmap> createPreviewBitmap(AnimatedImage image,Bitmap.Config bitmapConfig,int frameForPreview){
  CloseableReference<Bitmap> bitmap=createBitmap(image.getWidth(),image.getHeight(),bitmapConfig);
  AnimatedImageResult tempResult=AnimatedImageResult.forAnimatedImage(image);
  AnimatedDrawableBackend drawableBackend=mAnimatedDrawableBackendProvider.get(tempResult,null);
  AnimatedImageCompositor animatedImageCompositor=new AnimatedImageCompositor(drawableBackend,new AnimatedImageCompositor.Callback(){
    @Override public void onIntermediateResult(    int frameNumber,    Bitmap bitmap){
    }
    @Override public @Nullable CloseableReference<Bitmap> getCachedBitmap(    int frameNumber){
      return null;
    }
  }
);
  animatedImageCompositor.renderFrame(frameForPreview,bitmap.get());
  return bitmap;
}
