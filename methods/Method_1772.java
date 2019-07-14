/** 
 * Checks if array consisting of first headerSize bytes of imageHeaderBytes starts with png signature. More information on PNG can be found there: <a href="http://en.wikipedia.org/wiki/Portable_Network_Graphics"> http://en.wikipedia.org/wiki/Portable_Network_Graphics</a>
 * @param imageHeaderBytes
 * @param headerSize
 * @return true if imageHeaderBytes starts with PNG_HEADER
 */
private static boolean isPngHeader(final byte[] imageHeaderBytes,final int headerSize){
  return headerSize >= PNG_HEADER.length && ImageFormatCheckerUtils.startsWithPattern(imageHeaderBytes,PNG_HEADER);
}
