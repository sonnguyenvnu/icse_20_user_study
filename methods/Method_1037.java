private void readGraphicsControlExtension(int[] control) throws IOException {
  readNextByte();
  int flags=readNextByte();
  control[CONTROL_INDEX_DISPOSE]=(flags & 0x1c) >> 2;
  control[CONTROL_INDEX_DELAY]=readTwoByteInt() * 10;
  readNextByte();
  readNextByte();
}
