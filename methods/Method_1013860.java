/** 
 * Performs the Exif data extraction, adding found values to the specified instance of <code>Metadata</code>.
 * @param reader   The buffer reader from which Exif data should be read.
 * @param metadata The Metadata object into which extracted values should be merged.
 */
public void extract(@NotNull final BufferReader reader,@NotNull Metadata metadata){
  final ExifSubIFDDirectory directory=metadata.getOrCreateDirectory(ExifSubIFDDirectory.class);
  if (reader.getLength() <= 14) {
    directory.addError("Exif data segment must contain at least 14 bytes");
    return;
  }
  try {
    if (!reader.getString(0,6).equals("Exif\0\0")) {
      directory.addError("Exif data segment doesn't begin with 'Exif'");
      return;
    }
    extractIFD(metadata,metadata.getOrCreateDirectory(ExifIFD0Directory.class),TIFF_HEADER_START_OFFSET,reader);
  }
 catch (  BufferBoundsException e) {
    directory.addError("Exif data segment ended prematurely");
  }
}
