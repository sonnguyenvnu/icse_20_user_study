@Override public byte[] read() throws IOException {
  return Files.toByteArray(mFile);
}
