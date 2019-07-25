/** 
 * Deserialize (retrieve) this bitmap. See format specification at https://github.com/RoaringBitmap/RoaringFormatSpec The current bitmap is overwritten. It is not necessary that limit() on the input ByteBuffer indicates the end of the serialized data. After loading this RoaringBitmap, you can advance to the rest of the data (if there is more) by setting bbf.position(bbf.position() + bitmap.serializedSizeInBytes()); Note that the input ByteBuffer is effectively copied (with the slice operation) so you should expect the provided ByteBuffer to remain unchanged.
 * @param bbf the byte buffer (can be mapped, direct, array backed etc.
 * @throws IOException Signals that an I/O exception has occurred.
 */
public void deserialize(ByteBuffer bbf) throws IOException {
  try {
    this.highLowContainer.deserialize(bbf);
  }
 catch (  InvalidRoaringFormat cookie) {
    throw cookie.toIOException();
  }
}
