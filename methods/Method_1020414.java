/** 
 * returns the  {@code reader} as a string without closing it. 
 */
private static String slurp(Reader reader) throws IOException {
  StringBuilder to=new StringBuilder();
  CharBuffer buf=CharBuffer.allocate(AWS_IDENTITY_DOC_BUF_SIZE);
  while (reader.read(buf) != -1) {
    buf.flip();
    to.append(buf);
    buf.clear();
  }
  return to.toString();
}
