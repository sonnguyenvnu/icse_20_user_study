/** 
 * Gets suffix (for example jpg) with the specified content type.
 * @param contentType the specified content type
 * @return suffix
 */
public static String getSuffix(final String contentType){
  String ret;
  final String[] exts=MimeTypes.findExtensionsByMimeTypes(contentType,false);
  if (null != exts && 0 < exts.length) {
    ret=exts[0];
  }
 else {
    ret=StringUtils.substringAfter(contentType,"/");
    ret=StringUtils.substringBefore(ret,";");
  }
  return ret;
}
