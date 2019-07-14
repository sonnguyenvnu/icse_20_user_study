@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries) throws IOException {
  TokenManager tokenManager=getLexerForSource(sourceCode);
  tokenManager.setFileName(sourceCode.getFileName());
  try {
    final TokenFilter tokenFilter=getTokenFilter(tokenManager);
    GenericToken currentToken=tokenFilter.getNextToken();
    while (currentToken != null) {
      tokenEntries.add(processToken(tokenEntries,currentToken,sourceCode.getFileName()));
      currentToken=tokenFilter.getNextToken();
    }
  }
  finally {
    tokenEntries.add(TokenEntry.getEOF());
  }
}
