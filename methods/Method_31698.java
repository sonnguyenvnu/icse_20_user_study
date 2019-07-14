@Override public int read() throws IOException {
  int c=super.read();
  if (c != EMPTY_STREAM && (char)c == BOM) {
    return super.read();
  }
  return c;
}
