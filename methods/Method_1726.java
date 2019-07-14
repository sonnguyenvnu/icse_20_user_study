@Override public InputStream openStream() throws IOException {
  return new FileInputStream(mFile);
}
