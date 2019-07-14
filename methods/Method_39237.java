/** 
 * Resolves content type from all data.
 * @param contentType Content type if we know it. {@code null} is fine to use.
 * @return content type
 */
protected String resolveContentType(final String contentType){
  if (contentType != null) {
    return contentType;
  }
  if (name == null) {
    return MimeTypes.MIME_APPLICATION_OCTET_STREAM;
  }
  final String extension=FileNameUtil.getExtension(name);
  return MimeTypes.getMimeType(extension);
}
