/** 
 * Creates a bitmap from encoded bytes.
 * @param encodedImage the encoded image with a reference to the encoded bytes
 * @param bitmapConfig the {@link android.graphics.Bitmap.Config} used to create the decodedBitmap
 * @param regionToDecode optional image region to decode or null to decode the whole image
 * @param colorSpace the target color space of the decoded bitmap, must be one of the named colorspace in  {@link android.graphics.ColorSpace.Named}. If null, then SRGB color space is assumed if the SDK version >= 26.
 * @return the bitmap
 * @exception java.lang.OutOfMemoryError if the Bitmap cannot be allocated
 */
@Override public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage,Bitmap.Config bitmapConfig,@Nullable Rect regionToDecode,@Nullable final ColorSpace colorSpace){
  final BitmapFactory.Options options=getDecodeOptionsForStream(encodedImage,bitmapConfig);
  boolean retryOnFail=options.inPreferredConfig != Bitmap.Config.ARGB_8888;
  try {
    return decodeFromStream(encodedImage.getInputStream(),options,regionToDecode,colorSpace);
  }
 catch (  RuntimeException re) {
    if (retryOnFail) {
      return decodeFromEncodedImageWithColorSpace(encodedImage,Bitmap.Config.ARGB_8888,regionToDecode,colorSpace);
    }
    throw re;
  }
}
