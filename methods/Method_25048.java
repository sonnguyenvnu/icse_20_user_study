private static int checkedRead(int read) throws IOException {
  if (read < 0) {
    throw new EOFException();
  }
  return read;
}
