/** 
 * Positions the given input stream to the beginning of the EXIF data in the JPEG APP1 block.
 * @param is the input stream of jpeg image
 * @return length of EXIF data
 */
private static int moveToAPP1EXIF(InputStream is) throws IOException {
  if (moveToMarker(is,MARKER_APP1)) {
    int length=StreamProcessor.readPackedInt(is,2,false) - 2;
    if (length > 6) {
      int magic=StreamProcessor.readPackedInt(is,4,false);
      length-=4;
      int zero=StreamProcessor.readPackedInt(is,2,false);
      length-=2;
      if (magic == APP1_EXIF_MAGIC && zero == 0) {
        return length;
      }
    }
  }
  return 0;
}
