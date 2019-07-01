/** 
 * Reads a Jpeg image, removes all EXIF metadata (by removing the APP1 segment), and writes the result to a stream. <p>
 * @param src Byte array containing Jpeg image data.
 * @param os OutputStream to write the image to.
 */
public void _XXXXX_(final byte[] src,final OutputStream os) throws ImageReadException, IOException, ImageWriteException {
  final ByteSource byteSource=new ByteSourceArray(src);
  removeExifMetadata(byteSource,os);
}