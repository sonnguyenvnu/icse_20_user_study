@Override public boolean readFully(byte[] target,int offset,int length,boolean allowEndOfInput) throws IOException, InterruptedException {
  int bytesRead=readFromPeekBuffer(target,offset,length);
  while (bytesRead < length && bytesRead != C.RESULT_END_OF_INPUT) {
    bytesRead=readFromDataSource(target,offset,length,bytesRead,allowEndOfInput);
  }
  commitBytesRead(bytesRead);
  return bytesRead != C.RESULT_END_OF_INPUT;
}
