private static byte getByte(InputStream is) throws IOException {
  return (byte)(is.read() & 0xFF);
}
