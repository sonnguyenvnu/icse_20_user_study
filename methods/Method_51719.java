@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries){
  try (Tokenizer tokenizer=new Tokenizer(sourceCode.getCodeBuffer().toString())){
    Token token=tokenizer.getNextToken();
    while (!token.equals(Token.EOF)) {
      Token lookAhead=tokenizer.getNextToken();
      if (ignoreUsings && "using".equals(token.image) && !"(".equals(lookAhead.image)) {
        String randomTokenText=RandomStringUtils.randomAlphanumeric(20);
        token=new Token(randomTokenText,token.lineNumber);
        while (!";".equals(lookAhead.image) && !lookAhead.equals(Token.EOF)) {
          lookAhead=tokenizer.getNextToken();
        }
      }
      if (!";".equals(token.image)) {
        tokenEntries.add(new TokenEntry(token.image,sourceCode.getFileName(),token.lineNumber));
      }
      token=lookAhead;
    }
    tokenEntries.add(TokenEntry.getEOF());
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
