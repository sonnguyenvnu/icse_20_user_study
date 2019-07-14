/** 
 * Creates one buffer for the camera preview callback.  The size of the buffer is based off of the camera preview size and the format of the camera image.
 * @return a new preview buffer of the appropriate size for the current camera settings
 */
private byte[] createPreviewBuffer(Size previewSize){
  int bitsPerPixel=ImageFormat.getBitsPerPixel(ImageFormat.NV21);
  long sizeInBits=previewSize.getHeight() * previewSize.getWidth() * bitsPerPixel;
  int bufferSize=(int)Math.ceil(sizeInBits / 8.0d) + 1;
  byte[] byteArray=new byte[bufferSize];
  ByteBuffer buffer=ByteBuffer.wrap(byteArray);
  if (!buffer.hasArray() || (buffer.array() != byteArray)) {
    throw new IllegalStateException("Failed to create valid buffer for camera source.");
  }
  addBuffer(byteArray,buffer);
  return byteArray;
}
