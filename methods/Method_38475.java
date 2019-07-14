/** 
 * Defines body text and content type (as media type and charset). Body string will be converted to  {@link #body(String) raw body string}and "Content-Type" header will be set.
 */
public T bodyText(String body,final String mediaType,final String charset){
  body=StringUtil.convertCharset(body,charset,StringPool.ISO_8859_1);
  contentType(mediaType,charset);
  body(body);
  return _this();
}
