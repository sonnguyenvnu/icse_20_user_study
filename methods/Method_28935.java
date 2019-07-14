public void writeAsciiCrLf(final String in) throws IOException {
  final int size=in.length();
  for (int i=0; i != size; ++i) {
    if (count == buf.length) {
      flushBuffer();
    }
    buf[count++]=(byte)in.charAt(i);
  }
  writeCrLf();
}
