/** 
 * Decodes the bounds of an image and returns its width and height or null if the size can't be determined. It also recovers the color space of the image, or null if it can't be determined.
 * @param is the InputStream containing the image data
 * @return the metadata of the image
 */
public static ImageMetaData decodeDimensionsAndColorSpace(InputStream is){
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
    ColorSpace colorSpace=null;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      colorSpace=options.outColorSpace;
    }
    return new ImageMetaData(options.outWidth,options.outHeight,colorSpace);
  }
  finally {
    DECODE_BUFFERS.release(byteBuffer);
  }
}
