private void expect(final int type) throws IOException {
  if (tokenizer.ttype != type) {
    throw new IOException("Invalid Syntax.");
  }
  tokenizer.nextToken();
}
