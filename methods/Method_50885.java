private void addAndSkipLexicalErrors(SourceCode sourceCode) throws IOException {
  TokenEntry.State savedTokenEntry=new TokenEntry.State(tokens.getTokens());
  try {
    addAndThrowLexicalError(sourceCode);
  }
 catch (  TokenMgrError e) {
    System.err.println("Skipping " + sourceCode.getFileName() + ". Reason: " + e.getMessage());
    tokens.getTokens().clear();
    tokens.getTokens().addAll(savedTokenEntry.restore());
  }
}
