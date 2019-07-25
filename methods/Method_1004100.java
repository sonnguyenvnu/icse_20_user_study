private boolean accept(final int type) throws IOException {
  if (tokenizer.ttype == type) {
    tokenizer.nextToken();
    return true;
  }
  return false;
}
