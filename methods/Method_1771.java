/** 
 * Checks if imageHeaderBytes starts with SOI (start of image) marker, followed by 0xFF. If headerSize is lower than 3 false is returned. Description of jpeg format can be found here: <a href="http://www.w3.org/Graphics/JPEG/itu-t81.pdf"> http://www.w3.org/Graphics/JPEG/itu-t81.pdf</a> Annex B deals with compressed data format
 * @param imageHeaderBytes
 * @param headerSize
 * @return true if imageHeaderBytes starts with SOI_BYTES and headerSize >= 3
 */
private static boolean isJpegHeader(final byte[] imageHeaderBytes,final int headerSize){
  return headerSize >= JPEG_HEADER.length && ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes,JPEG_HEADER);
}
