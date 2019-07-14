/** 
 * Returns buffered content as bytes, no matter if stream or writer is used. Returns <code>null</code> if buffering was not enabled.
 */
public byte[] getBufferContentAsBytes(){
  if (buffer == null) {
    return null;
  }
  if (buffer.isUsingStream()) {
    return buffer.toByteArray();
  }
  char[] content=buffer.toCharArray();
  String encoding=getContentTypeEncoding();
  if (encoding == null) {
    return CharUtil.toByteArray(content);
  }
 else {
    return CharUtil.toByteArray(content,encoding);
  }
}
