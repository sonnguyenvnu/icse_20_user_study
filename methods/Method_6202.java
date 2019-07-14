public int readSyncsafeInt() throws IOException {
  return ((readByte() & 0x7F) << 21) | ((readByte() & 0x7F) << 14) | ((readByte() & 0x7F) << 7) | (readByte() & 0x7F);
}
