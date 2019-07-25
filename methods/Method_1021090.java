@Override public void close() throws IOException {
  try {
    reassign(Collections.emptySet());
  }
 catch (  final InvalidCursorException e) {
    throw new IOException(e);
  }
}
