private void init(String query) throws ParseCancellationException {
  if (Objects.equals(this.query,query)) {
    return;
  }
  SearchLexer lexer=new SearchLexer(new ANTLRInputStream(query));
  lexer.removeErrorListeners();
  lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
  SearchParser parser=new SearchParser(new CommonTokenStream(lexer));
  parser.removeErrorListeners();
  parser.addErrorListener(ThrowingErrorListener.INSTANCE);
  parser.setErrorHandler(new BailErrorStrategy());
  tree=parser.start();
  this.query=query;
}
