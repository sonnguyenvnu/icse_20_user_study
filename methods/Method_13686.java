@Override public InputStream getInputStream() throws IOException {
  assertExisted();
  if (isBucket()) {
    throw new IllegalStateException("Cannot open an input stream to a bucket: '" + this.location + "'");
  }
 else {
    return getOSSObject().getObjectContent();
  }
}
