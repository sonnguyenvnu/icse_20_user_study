public void read(byte[] bytes,int offset,int length) throws IOException {
  try {
    this.buffer.get(bytes,offset,length);
  }
 catch (  BufferUnderflowException var5) {
    throw new EOFException();
  }
}
