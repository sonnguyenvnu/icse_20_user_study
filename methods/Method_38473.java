/** 
 * Returns <b>raw</b> body bytes. Returns <code>null</code> if body is not specified.
 */
public byte[] bodyBytes(){
  if (body == null) {
    return null;
  }
  try {
    return body.getBytes(StringPool.ISO_8859_1);
  }
 catch (  UnsupportedEncodingException ignore) {
    return null;
  }
}
