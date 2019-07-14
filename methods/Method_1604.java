/** 
 * Creates a bitmap from encoded JPEG bytes. Supports a partial JPEG image.
 * @param encodedImage the encoded image with reference to the encoded bytes
 * @param bitmapConfig the {@link android.graphics.Bitmap.Config} used to create the decodedBitmap
 * @param regionToDecode optional image region to decode. currently not supported.
 * @param length the number of encoded bytes in the buffer
 * @param colorSpace the target color space of the decoded bitmap, must be one of the named colorspace in  {@link android.graphics.ColorSpace.Named}. If null, then SRGB color space is assumed if the SDK version >= 26.
 * @return the bitmap
 * @throws TooManyBitmapsException if the pool is full
 * @throws java.lang.OutOfMemoryError if the Bitmap cannot be allocated
 */
@Override public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(final EncodedImage encodedImage,Bitmap.Config bitmapConfig,@Nullable Rect regionToDecode,int length,@Nullable final ColorSpace colorSpace){
  BitmapFactory.Options options=getBitmapFactoryOptions(encodedImage.getSampleSize(),bitmapConfig);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    OreoUtils.setColorSpace(options,colorSpace);
  }
  final CloseableReference<PooledByteBuffer> bytesRef=encodedImage.getByteBufferRef();
  Preconditions.checkNotNull(bytesRef);
  try {
    Bitmap bitmap=decodeJPEGByteArrayAsPurgeable(bytesRef,length,options);
    return pinBitmap(bitmap);
  }
  finally {
    CloseableReference.closeSafely(bytesRef);
  }
}
