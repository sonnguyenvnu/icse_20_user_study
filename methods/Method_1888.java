private static int getInt(InputStream is) throws IOException {
  byte byte1=(byte)is.read();
  byte byte2=(byte)is.read();
  byte byte3=(byte)is.read();
  byte byte4=(byte)is.read();
  return (byte4 << 24) & 0xFF000000 | (byte3 << 16) & 0xFF0000 | (byte2 << 8) & 0xFF00 | (byte1) & 0xFF;
}
