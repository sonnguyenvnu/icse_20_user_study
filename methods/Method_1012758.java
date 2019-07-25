@Override public int available() throws IOException {
  return (int)outputStream.getWriteCount();
}
