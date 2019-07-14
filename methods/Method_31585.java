protected static int getLastKeywordIndex(List<Token> tokens,int endIndex){
  for (int i=endIndex - 1; i >= 0; i--) {
    Token token=tokens.get(i);
    if (token.getType() == TokenType.KEYWORD) {
      return i;
    }
  }
  return -1;
}
