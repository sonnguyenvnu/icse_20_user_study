/** 
 * Reads orientation information from TIFF data.
 * @param is the input stream of TIFF data
 * @param length length of the TIFF data
 * @return orientation information (1/3/6/8 on success, 0 if not found)
 */
public static int readOrientationFromTIFF(InputStream is,int length) throws IOException {
  TiffHeader tiffHeader=new TiffHeader();
  length=readTiffHeader(is,length,tiffHeader);
  int toSkip=tiffHeader.firstIfdOffset - 8;
  if (length == 0 || toSkip > length) {
    return 0;
  }
  is.skip(toSkip);
  length-=toSkip;
  length=moveToTiffEntryWithTag(is,length,tiffHeader.isLittleEndian,TIFF_TAG_ORIENTATION);
  return getOrientationFromTiffEntry(is,length,tiffHeader.isLittleEndian);
}
