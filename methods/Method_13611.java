@Override public InputStream getBody() throws IOException {
  return new ByteArrayInputStream(blockResponse.getBytes());
}
