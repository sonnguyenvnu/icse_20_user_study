/** 
 * Reads the orientation information from the TIFF entry. It is assumed that the entry has a TIFF orientation tag and that tag has already been consumed.
 * @param is the input stream positioned at the TIFF entry with tag already being consumed
 * @param isLittleEndian whether the TIFF data is stored in little or big endian format
 * @return Orientation value in TIFF IFD entry.
 */
private static int getOrientationFromTiffEntry(InputStream is,int length,boolean isLittleEndian) throws IOException {
  if (length < 10) {
    return 0;
  }
  int type=StreamProcessor.readPackedInt(is,2,isLittleEndian);
  if (type != TIFF_TYPE_SHORT) {
    return 0;
  }
  int count=StreamProcessor.readPackedInt(is,4,isLittleEndian);
  if (count != 1) {
    return 0;
  }
  int value=StreamProcessor.readPackedInt(is,2,isLittleEndian);
  int padding=StreamProcessor.readPackedInt(is,2,isLittleEndian);
  return value;
}
