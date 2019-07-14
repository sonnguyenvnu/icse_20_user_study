/** 
 * Defines download file name and mime type from the name extension.
 */
public RawData downloadableAs(final String downloadFileName){
  this.downloadFileName=downloadFileName;
  this.mimeType=MimeTypes.getMimeType(FileNameUtil.getExtension(downloadFileName));
  return this;
}
