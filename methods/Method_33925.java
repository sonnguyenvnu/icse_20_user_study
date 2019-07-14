private static byte[] readBigInteger(ByteArrayInputStream in) throws IOException {
  byte[] b=new byte[4];
  if (in.read(b) != 4) {
    throw new IOException("Expected length data as 4 bytes");
  }
  int l=(b[0] << 24) | (b[1] << 16) | (b[2] << 8) | b[3];
  b=new byte[l];
  if (in.read(b) != l) {
    throw new IOException("Expected " + l + " key bytes");
  }
  return b;
}
