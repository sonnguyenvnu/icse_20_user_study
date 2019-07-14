@Override public void tokenize(SourceCode source,Tokens cpdTokens){
  String filename=source.getFileName();
  try {
    Lexer lexer=new Lexer();
    List<Token> tokens=lexer.getTokensOfFile(filename);
    for (    Token token : tokens) {
      String tokenVal=token.tokenVal() != null ? token.tokenVal() : Integer.toString(token.tokenType());
      TokenEntry cpdToken=new TokenEntry(tokenVal,filename,token.line());
      cpdTokens.add(cpdToken);
    }
    cpdTokens.add(TokenEntry.getEOF());
  }
 catch (  RuntimeException e) {
    e.printStackTrace();
    throw new TokenMgrError("Lexical error in file " + filename + ". The scala tokenizer exited with error: " + e.getMessage(),TokenMgrError.LEXICAL_ERROR);
  }
}
