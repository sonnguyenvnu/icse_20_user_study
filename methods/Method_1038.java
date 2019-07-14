private int readIntoBlock(int offset,int length) throws IOException {
  int count=mInputStream.read(block,offset,length);
  if (count == -1) {
    throw new EOFException("Unexpected end of gif file");
  }
  return count;
}
