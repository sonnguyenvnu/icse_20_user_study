@Override public void tokenize(final SourceCode sourceCode,final Tokens tokenEntries){
  final AntlrTokenManager tokenManager=getLexerForSource(sourceCode);
  tokenManager.setFileName(sourceCode.getFileName());
  final AntlrTokenFilter tokenFilter=getTokenFilter(tokenManager);
  try {
    AntlrToken currentToken=tokenFilter.getNextToken();
    while (currentToken != null) {
      processToken(tokenEntries,tokenManager.getFileName(),currentToken);
      currentToken=tokenFilter.getNextToken();
    }
  }
 catch (  final AntlrTokenManager.ANTLRSyntaxError err) {
    throw new TokenMgrError("Lexical error in file " + tokenManager.getFileName() + " at line " + err.getLine() + ", column " + err.getColumn() + ".  Encountered: " + err.getMessage(),TokenMgrError.LEXICAL_ERROR);
  }
 finally {
    tokenEntries.add(TokenEntry.getEOF());
  }
}
