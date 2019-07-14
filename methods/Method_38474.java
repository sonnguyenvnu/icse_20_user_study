/** 
 * Returns  {@link #body() body content} as text. If {@link #charset() charset parameter}of "Content-Type" header is defined, body string charset is converted, otherwise the same raw body content is returned. Never returns <code>null</code>.
 */
public String bodyText(){
  if (body == null) {
    return StringPool.EMPTY;
  }
  if (charset != null) {
    return StringUtil.convertCharset(body,StringPool.ISO_8859_1,charset);
  }
  return body();
}
