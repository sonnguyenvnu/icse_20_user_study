public void decode() throws IOException {
  if (mDecoded) {
    throw new IllegalStateException("decode called multiple times");
  }
  mDecoded=true;
  readGifInfo();
}
