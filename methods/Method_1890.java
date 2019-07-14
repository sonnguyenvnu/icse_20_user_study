private static int read3Bytes(InputStream is) throws IOException {
  byte byte1=getByte(is);
  byte byte2=getByte(is);
  byte byte3=getByte(is);
  return (((int)byte3) << 16 & 0xFF0000) | (((int)byte2) << 8 & 0xFF00) | (((int)byte1) & 0xFF);
}
