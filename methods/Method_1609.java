/** 
 * Creates a bitmap from encoded JPEG bytes. Supports a partial JPEG image.
 * @param encodedImage the encoded image with reference to the encoded bytes
 * @param bitmapConfig the {@link android.graphics.Bitmap.Config} used to create the decodedBitmap
 * @param regionToDecode optional image region to decode or null to decode the whole image
 * @param length the number of encoded bytes in the buffer
 * @param colorSpace the target color space of the decoded bitmap, must be one of the named colorspace in  {@link android.graphics.ColorSpace.Named}. If null, then SRGB color space is assumed if the SDK version >= 26.
 * @return the bitmap
 * @exception java.lang.OutOfMemoryError if the Bitmap cannot be allocated
 */
@Override public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage,Bitmap.Config bitmapConfig,@Nullable Rect regionToDecode,int length,@Nullable final ColorSpace colorSpace){
  boolean isJpegComplete=encodedImage.isCompleteAt(length);
  final BitmapFactory.Options options=getDecodeOptionsForStream(encodedImage,bitmapConfig);
  InputStream jpegDataStream=encodedImage.getInputStream();
  Preconditions.checkNotNull(jpegDataStream);
  if (encodedImage.getSize() > length) {
    jpegDataStream=new LimitedInputStream(jpegDataStream,length);
  }
  if (!isJpegComplete) {
    jpegDataStream=new TailAppendingInputStream(jpegDataStream,EOI_TAIL);
  }
  boolean retryOnFail=options.inPreferredConfig != Bitmap.Config.ARGB_8888;
  try {
    return decodeFromStream(jpegDataStream,options,regionToDecode,colorSpace);
  }
 catch (  RuntimeException re) {
    if (retryOnFail) {
      return decodeJPEGFromEncodedImageWithColorSpace(encodedImage,Bitmap.Config.ARGB_8888,regionToDecode,length,colorSpace);
    }
    throw re;
  }
}
