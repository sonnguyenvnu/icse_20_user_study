private void skipImage() throws IOException {
  readTwoByteInt();
  readTwoByteInt();
  readTwoByteInt();
  readTwoByteInt();
  int flags=readNextByte();
  boolean hasLct=(flags & 0x80) != 0;
  if (hasLct) {
    int lctSize=2 << (flags & 7);
    ignoreColorTable(lctSize);
  }
  readNextByte();
  skipExtension();
}
