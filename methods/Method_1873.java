/** 
 * Decodes the bounds of an image and returns its width and height or null if the size can't be determined
 * @param is the InputStream containing the image data
 * @return dimensions of the image
 */
public static @Nullable Pair<Integer,Integer> decodeDimensions(InputStream is){
  Preconditions.checkNotNull(is);
  ByteBuffer byteBuffer=DECODE_BUFFERS.acquire();
  if (byteBuffer == null) {
    byteBuffer=ByteBuffer.allocate(DECODE_BUFFER_SIZE);
  }
  BitmapFactory.Options options=new BitmapFactory.Options();
  options.inJustDecodeBounds=true;
  try {
    options.inTempStorage=byteBuffer.array();
    BitmapFactory.decodeStream(is,null,options);
    return (options.outWidth == -1 || options.outHeight == -1) ? null : new Pair<>(options.outWidth,options.outHeight);
  }
  finally {
    DECODE_BUFFERS.release(byteBuffer);
  }
}
