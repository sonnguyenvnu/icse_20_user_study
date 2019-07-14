/** 
 * Appends string to the buffer.
 */
public void print(final String string) throws IOException {
  if (isBufferStreamBased()) {
    String encoding=getContentTypeEncoding();
    byte[] bytes;
    if (encoding == null) {
      bytes=string.getBytes();
    }
 else {
      bytes=string.getBytes(encoding);
    }
    buffer.getOutputStream().write(bytes);
    return;
  }
  buffer.getWriter().write(string);
}
