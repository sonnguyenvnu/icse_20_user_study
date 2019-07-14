public static int get2BytesAsInt(InputStream is) throws IOException {
  byte byte1=(byte)is.read();
  byte byte2=(byte)is.read();
  return (byte2 << 8 & 0xFF00) | (byte1 & 0xFF);
}
