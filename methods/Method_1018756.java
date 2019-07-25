public void write(String str,int off,int len) throws IOException {
  checkClosed();
  try {
    int charsWritten=parentClob.setString(streamPos,str,off,len);
    streamPos+=charsWritten;
  }
 catch (  SQLException ex) {
    throw new IOException(ex.getMessage());
  }
}
