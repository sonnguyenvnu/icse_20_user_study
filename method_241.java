/** 
 * Returns the data of the first JPEG thumbnail found in the EXIF metadata.
 * @return JPEG data or null if no thumbnail.
 * @throws ImageReadException
 * @throws IOException
 */
public byte[] _XXXXX_() throws ImageReadException, IOException {
  if (exif == null) {
    return null;
  }
  final List<? extends ImageMetadataItem> dirs=exif.getDirectories();
  for (  final ImageMetadataItem d : dirs) {
    final TiffImageMetadata.Directory dir=(TiffImageMetadata.Directory)d;
    byte[] data=null;
    if (dir.getJpegImageData() != null) {
      data=dir.getJpegImageData().getData();
    }
    if (data != null) {
      return data;
    }
  }
  return null;
}