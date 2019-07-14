/** 
 * Prepares response for file download with provided mime type.
 */
public static void prepareDownload(final HttpServletResponse response,final File file,final String mimeType){
  if (!file.exists()) {
    throw new IllegalArgumentException("File not found: " + file);
  }
  if (file.length() > Integer.MAX_VALUE) {
    throw new IllegalArgumentException("File too big: " + file);
  }
  prepareResponse(response,file.getAbsolutePath(),mimeType,(int)file.length());
}
