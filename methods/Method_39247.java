/** 
 * Extracts encoding from a given content type.
 * @param contentType content type.
 * @return Encoding from the content type. May return {@code null} if encoding is not specified in content type.
 */
public static String extractEncoding(final String contentType){
  int ndx=contentType.indexOf(';');
  final String charset=ndx != -1 ? contentType.substring(ndx + 1) : StringPool.EMPTY;
  String encoding=null;
  ndx=charset.indexOf(ATTR_CHARSET);
  if (ndx != -1) {
    ndx+=ATTR_CHARSET.length();
    final int len=charset.length();
    if (charset.charAt(ndx) == '"') {
      ndx++;
    }
    final int start=ndx;
    while (ndx < len) {
      final char c=charset.charAt(ndx);
      if ((c == '"') || (CharUtil.isWhitespace(c)) || (c == ';')) {
        break;
      }
      ndx++;
    }
    encoding=charset.substring(start,ndx);
  }
  return encoding;
}
