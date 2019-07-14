public byte[] readBytes() throws IOException {
  return readBytes((int)getRemaining());
}
