@Override public InputStream openInputStream() throws IOException {
  return new FileInputStream(file);
}
