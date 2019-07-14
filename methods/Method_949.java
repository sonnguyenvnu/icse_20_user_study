/** 
 * Converts the given image reference to a bitmap reference and closes the original image reference.
 * @param closeableImage the image to convert. It will be closed afterwards and will be invalid
 * @return the closeable bitmap reference to be used
 */
@VisibleForTesting @Nullable static CloseableReference<Bitmap> convertToBitmapReferenceAndClose(final @Nullable CloseableReference<CloseableImage> closeableImage){
  try {
    if (CloseableReference.isValid(closeableImage) && closeableImage.get() instanceof CloseableStaticBitmap) {
      CloseableStaticBitmap closeableStaticBitmap=(CloseableStaticBitmap)closeableImage.get();
      if (closeableStaticBitmap != null) {
        return closeableStaticBitmap.cloneUnderlyingBitmapReference();
      }
    }
    return null;
  }
  finally {
    CloseableReference.closeSafely(closeableImage);
  }
}
