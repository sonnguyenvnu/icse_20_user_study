private void ensureNotClosed() throws IOException {
  if (mClosed) {
    throw new IOException("stream already closed");
  }
}
