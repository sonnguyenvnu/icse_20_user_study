/** 
 * Gets suffix (for example jpg) of the specified file.
 * @param file the specified file
 * @return suffix
 */
public static String getSuffix(final FileUpload file){
  final String fileName=file.getHeader().getFileName();
  String ret=StringUtils.substringAfterLast(fileName,".");
  if (StringUtils.isNotBlank(ret)) {
    return ret;
  }
  final String contentType=file.getHeader().getContentType();
  return getSuffix(contentType);
}
