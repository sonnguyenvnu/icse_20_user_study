/** 
 * Decodes a GIF into a CloseableImage.
 * @param encodedImage encoded image (native byte array holding the encoded bytes and meta data)
 * @param options the options for the decode
 * @param bitmapConfig the Bitmap.Config used to generate the output bitmaps
 * @return a {@link CloseableImage} for the GIF image
 */
public CloseableImage decodeGif(final EncodedImage encodedImage,final ImageDecodeOptions options,final Bitmap.Config bitmapConfig){
  if (sGifAnimatedImageDecoder == null) {
    throw new UnsupportedOperationException("To encode animated gif please add the dependency " + "to the animated-gif module");
  }
  final CloseableReference<PooledByteBuffer> bytesRef=encodedImage.getByteBufferRef();
  Preconditions.checkNotNull(bytesRef);
  try {
    final PooledByteBuffer input=bytesRef.get();
    AnimatedImage gifImage;
    if (input.getByteBuffer() != null) {
      gifImage=sGifAnimatedImageDecoder.decode(input.getByteBuffer());
    }
 else {
      gifImage=sGifAnimatedImageDecoder.decode(input.getNativePtr(),input.size());
    }
    return getCloseableImage(options,gifImage,bitmapConfig);
  }
  finally {
    CloseableReference.closeSafely(bytesRef);
  }
}
