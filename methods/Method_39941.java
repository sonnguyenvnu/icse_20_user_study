/** 
 * Returns buffered content as chars, no matter if stream or writer is used. Returns <code>null</code> if buffering was not enabled.
 */
public char[] getBufferContentAsChars(){
  if (buffer == null) {
    return null;
  }
  if (!buffer.isUsingStream()) {
    return buffer.toCharArray();
  }
  byte[] content=buffer.toByteArray();
  String encoding=getContentTypeEncoding();
  if (encoding == null) {
    return CharUtil.toCharArray(content);
  }
 else {
    return CharUtil.toCharArray(content,encoding);
  }
}
