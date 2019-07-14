private static int getBitmapSizeBytes(@Nullable CloseableReference<CloseableImage> imageReference){
  if (!CloseableReference.isValid(imageReference)) {
    return 0;
  }
  return getBitmapSizeBytes(imageReference.get());
}
