/** 
 * Checks if first headerSize bytes of imageHeaderBytes constitute a valid header for a bmp image. Details on BMP header can be found <a href="http://www.onicos.com/staff/iz/formats/bmp.html"> </a>
 * @param imageHeaderBytes
 * @param headerSize
 * @return true if imageHeaderBytes is a valid header for a bmp image
 */
private static boolean isBmpHeader(final byte[] imageHeaderBytes,final int headerSize){
  if (headerSize < BMP_HEADER.length) {
    return false;
  }
  return ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes,BMP_HEADER);
}
