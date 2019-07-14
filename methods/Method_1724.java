@Override public InputStream openStream() throws IOException {
  return new ByteArrayInputStream(mBytes);
}
