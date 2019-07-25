/** 
 * Performs the Jpeg data extraction, adding found values to the specified instance of <code>Metadata</code>.
 */
public void extract(@NotNull final BufferReader reader,@NotNull Metadata metadata){
  JpegCommentDirectory directory=metadata.getOrCreateDirectory(JpegCommentDirectory.class);
  try {
    directory.setString(JpegCommentDirectory.TAG_JPEG_COMMENT,reader.getString(0,(int)reader.getLength()));
  }
 catch (  BufferBoundsException e) {
    directory.addError("Exception reading JPEG comment string");
  }
}
