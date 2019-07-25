@Override public int available() throws IOException {
  if (uncompressedCursor < uncompressedLimit) {
    return uncompressedLimit - uncompressedCursor;
  }
 else {
    if (hasNextChunk()) {
      return uncompressedLimit - uncompressedCursor;
    }
 else {
      return 0;
    }
  }
}
