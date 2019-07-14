/** 
 * Converts  {@link File} {@link URL}s to file name. Accepts only  {@link URL}s with 'file' protocol. Otherwise, for other schemes returns  {@code null}.
 * @param url {@link URL} to convert
 * @return file name
 */
public static String toFileName(final URL url){
  if ((url == null) || !(url.getProtocol().equals(FILE_PROTOCOL))) {
    return null;
  }
  String filename=url.getFile().replace('/',File.separatorChar);
  return URLDecoder.decode(filename,encoding());
}
