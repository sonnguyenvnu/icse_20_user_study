@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries){
  StringBuilder code=sourceCode.getCodeBuffer();
  ANTLRStringStream ass=new ANTLRStringStream(code.toString());
  ApexLexer lexer=new ApexLexer(ass){
    @Override public void emitErrorMessage(    String msg){
      throw new TokenMgrError(msg,TokenMgrError.LEXICAL_ERROR);
    }
  }
;
  try {
    Token token=lexer.nextToken();
    while (token.getType() != Token.EOF) {
      if (token.getChannel() != Lexer.HIDDEN) {
        String tokenText=token.getText();
        if (!caseSensitive) {
          tokenText=tokenText.toLowerCase(Locale.ROOT);
        }
        TokenEntry tokenEntry=new TokenEntry(tokenText,sourceCode.getFileName(),token.getLine());
        tokenEntries.add(tokenEntry);
      }
      token=lexer.nextToken();
    }
  }
  finally {
    tokenEntries.add(TokenEntry.getEOF());
  }
}
