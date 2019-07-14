/** 
 * Positions the given input stream to the entry that has a specified tag. Tag will be consumed.
 * @param is the input stream of TIFF data positioned to the beginning of an IFD.
 * @param length length of the available data in the given input stream.
 * @param isLittleEndian whether the TIFF data is stored in little or big endian format
 * @param tagToFind tag to find
 * @return remaining length of the data on success, 0 on failure
 */
private static int moveToTiffEntryWithTag(InputStream is,int length,boolean isLittleEndian,int tagToFind) throws IOException {
  if (length < 14) {
    return 0;
  }
  int numEntries=StreamProcessor.readPackedInt(is,2,isLittleEndian);
  length-=2;
  while (numEntries-- > 0 && length >= 12) {
    int tag=StreamProcessor.readPackedInt(is,2,isLittleEndian);
    length-=2;
    if (tag == tagToFind) {
      return length;
    }
    is.skip(10);
    length-=10;
  }
  return 0;
}
