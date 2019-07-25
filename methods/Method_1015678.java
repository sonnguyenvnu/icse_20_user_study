public int available() throws IOException {
  return input.limit() - input.position();
}
