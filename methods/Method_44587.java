@Override public LunoQuote exerciseQuote(String quoteId) throws IOException, LunoException {
  return luno.exerciseQuote(this.auth,quoteId);
}
