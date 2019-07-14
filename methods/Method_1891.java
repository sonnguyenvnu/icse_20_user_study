private static short getShort(InputStream is) throws IOException {
  return (short)(is.read() & 0xFF);
}
