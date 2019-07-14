private static List<ApexDocTokenLocation> buildApexDocTokenLocations(String source){
  ANTLRStringStream stream=new ANTLRStringStream(source);
  ApexLexer lexer=new ApexLexer(stream);
  List<ApexDocTokenLocation> tokenLocations=new LinkedList<>();
  int startIndex=0;
  Token token=lexer.nextToken();
  int endIndex=lexer.getCharIndex();
  while (token.getType() != Token.EOF) {
    if (token.getType() == ApexLexer.BLOCK_COMMENT) {
      if (token.getText().startsWith("/**")) {
        tokenLocations.add(new ApexDocTokenLocation(startIndex,token.getText()));
      }
    }
    startIndex=endIndex;
    token=lexer.nextToken();
    endIndex=lexer.getCharIndex();
  }
  return tokenLocations;
}
