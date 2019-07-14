@Override public LunoQuote discardQuote(String quoteId) throws IOException, LunoException {
  return luno.discardQuote(this.auth,quoteId);
}
