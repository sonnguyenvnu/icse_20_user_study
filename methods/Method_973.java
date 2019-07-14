/** 
 * Decode a WebP into a CloseableImage.
 * @param encodedImage encoded image (native byte array holding the encoded bytes and meta data)
 * @param options the options for the decode
 * @param bitmapConfig the Bitmap.Config used to generate the output bitmaps
 * @return a {@link CloseableImage} for the WebP image
 */
public CloseableImage decodeWebP(final EncodedImage encodedImage,final ImageDecodeOptions options,final Bitmap.Config bitmapConfig){
  if (sWebpAnimatedImageDecoder == null) {
    throw new UnsupportedOperationException("To encode animated webp please add the dependency " + "to the animated-webp module");
  }
  final CloseableReference<PooledByteBuffer> bytesRef=encodedImage.getByteBufferRef();
  Preconditions.checkNotNull(bytesRef);
  try {
    final PooledByteBuffer input=bytesRef.get();
    AnimatedImage webPImage;
    if (input.getByteBuffer() != null) {
      webPImage=sWebpAnimatedImageDecoder.decode(input.getByteBuffer());
    }
 else {
      webPImage=sWebpAnimatedImageDecoder.decode(input.getNativePtr(),input.size());
    }
    return getCloseableImage(options,webPImage,bitmapConfig);
  }
  finally {
    CloseableReference.closeSafely(bytesRef);
  }
}
