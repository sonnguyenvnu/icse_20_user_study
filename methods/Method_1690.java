/** 
 * Clients should override this method only if the post-processed bitmap has to be of a different size than the source bitmap. If the post-processed bitmap is of the same size, clients should override one of the other two methods. <p> The source bitmap must not be modified as it may be shared by the other clients. The implementation must create a new bitmap that is safe to be modified and return a reference to it. Clients should use <code>bitmapFactory</code> to create a new bitmap.
 * @param sourceBitmap The source bitmap.
 * @param bitmapFactory The factory to create a destination bitmap.
 * @return a reference to the newly created bitmap
 */
@Override public CloseableReference<Bitmap> process(Bitmap sourceBitmap,PlatformBitmapFactory bitmapFactory){
  final Bitmap.Config sourceBitmapConfig=sourceBitmap.getConfig();
  CloseableReference<Bitmap> destBitmapRef=bitmapFactory.createBitmapInternal(sourceBitmap.getWidth(),sourceBitmap.getHeight(),sourceBitmapConfig != null ? sourceBitmapConfig : FALLBACK_BITMAP_CONFIGURATION);
  try {
    process(destBitmapRef.get(),sourceBitmap);
    return CloseableReference.cloneOrNull(destBitmapRef);
  }
  finally {
    CloseableReference.closeSafely(destBitmapRef);
  }
}
