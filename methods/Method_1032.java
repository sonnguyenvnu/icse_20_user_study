private void validateAndIgnoreHeader() throws IOException {
  readIntoBlock(0,6);
  boolean valid='G' == (char)block[0] && 'I' == (char)block[1] && 'F' == (char)block[2] && '8' == (char)block[3] && ('7' == (char)block[4] || '9' == (char)block[4]) && 'a' == (char)block[5];
  if (!valid) {
    throw new IOException("Illegal header for gif");
  }
  readTwoByteInt();
  readTwoByteInt();
  int fields=readNextByte();
  boolean hasGlobalColorTable=(fields & 0x80) != 0;
  int globalColorTableSize=2 << (fields & 7);
  readNextByte();
  readNextByte();
  if (hasGlobalColorTable) {
    ignoreColorTable(globalColorTableSize);
  }
}
