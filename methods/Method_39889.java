/** 
 * Prepares response for various provided data.
 * @param response http response
 * @param fileName file name, if full path then file name will be stripped, if null, will be ignored.
 * @param mimeType mime type with optional charset, may be <code>null</code>
 * @param fileSize if less then 0 it will be ignored
 */
public static void prepareResponse(final HttpServletResponse response,final String fileName,String mimeType,final int fileSize){
  if ((mimeType == null) && (fileName != null)) {
    String extension=FileNameUtil.getExtension(fileName);
    mimeType=MimeTypes.getMimeType(extension);
  }
  if (mimeType != null) {
    response.setContentType(mimeType);
  }
  if (fileSize >= 0) {
    response.setContentLength(fileSize);
  }
  if (fileName != null) {
    String name=FileNameUtil.getName(fileName);
    String encodedFileName=URLCoder.encode(name);
    response.setHeader(CONTENT_DISPOSITION,"attachment;filename=\"" + name + "\";filename*=utf8''" + encodedFileName);
  }
}
