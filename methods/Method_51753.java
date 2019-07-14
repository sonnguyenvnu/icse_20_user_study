@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries){
  StringBuilder buffer=sourceCode.getCodeBuffer();
  GroovyLexer lexer=new GroovyLexer(new StringReader(buffer.toString()));
  TokenStream tokenStream=lexer.plumb();
  try {
    Token token=tokenStream.nextToken();
    while (token.getType() != Token.EOF_TYPE) {
      TokenEntry tokenEntry=new TokenEntry(token.getText(),sourceCode.getFileName(),token.getLine());
      tokenEntries.add(tokenEntry);
      token=tokenStream.nextToken();
    }
  }
 catch (  TokenStreamException err) {
    throw new TokenMgrError("Lexical error in file " + sourceCode.getFileName() + " at line " + lexer.getLine() + ", column " + lexer.getColumn() + ".  Encountered: " + err.getMessage(),TokenMgrError.LEXICAL_ERROR);
  }
 finally {
    tokenEntries.add(TokenEntry.getEOF());
  }
}
