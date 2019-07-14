private CloseableImage getCloseableImage(ImageDecodeOptions options,AnimatedImage image,Bitmap.Config bitmapConfig){
  List<CloseableReference<Bitmap>> decodedFrames=null;
  CloseableReference<Bitmap> previewBitmap=null;
  try {
    final int frameForPreview=options.useLastFrameForPreview ? image.getFrameCount() - 1 : 0;
    if (options.forceStaticImage) {
      return new CloseableStaticBitmap(createPreviewBitmap(image,bitmapConfig,frameForPreview),ImmutableQualityInfo.FULL_QUALITY,0);
    }
    if (options.decodeAllFrames) {
      decodedFrames=decodeAllFrames(image,bitmapConfig);
      previewBitmap=CloseableReference.cloneOrNull(decodedFrames.get(frameForPreview));
    }
    if (options.decodePreviewFrame && previewBitmap == null) {
      previewBitmap=createPreviewBitmap(image,bitmapConfig,frameForPreview);
    }
    AnimatedImageResult animatedImageResult=AnimatedImageResult.newBuilder(image).setPreviewBitmap(previewBitmap).setFrameForPreview(frameForPreview).setDecodedFrames(decodedFrames).build();
    return new CloseableAnimatedImage(animatedImageResult);
  }
  finally {
    CloseableReference.closeSafely(previewBitmap);
    CloseableReference.closeSafely(decodedFrames);
  }
}
