public void write(byte[] b,int off,int len) throws IOException {
  if (null == b)   return;
  try {
    String s=new String(b,off,len,StandardCharsets.US_ASCII);
    int charsWritten=parentClob.setString(streamPos,s);
    streamPos+=charsWritten;
  }
 catch (  SQLException ex) {
    throw new IOException(ex.getMessage());
  }
}
