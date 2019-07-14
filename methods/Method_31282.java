@Override protected void adjustBlockDepth(ParserContext context,List<Token> tokens,Token keyword){
  boolean previousTokenIsKeyword=!tokens.isEmpty() && tokens.get(tokens.size() - 1).getType() == TokenType.KEYWORD;
  int lastKeywordIndex=getLastKeywordIndex(tokens);
  Token previousKeyword=lastKeywordIndex >= 0 ? tokens.get(lastKeywordIndex) : null;
  lastKeywordIndex=getLastKeywordIndex(tokens,lastKeywordIndex);
  Token previousPreviousToken=lastKeywordIndex >= 0 ? tokens.get(lastKeywordIndex) : null;
  if (("BEGIN".equals(keyword.getText()) && (!"ROW".equals(previousKeyword.getText()) || previousPreviousToken == null || "EACH".equals(previousPreviousToken.getText()))) || (("CASE".equals(keyword.getText()) || "DO".equals(keyword.getText()) || "IF".equals(keyword.getText()) || "REPEAT".equals(keyword.getText())))) {
    if (!previousTokenIsKeyword || !"END".equals(previousKeyword.getText())) {
      context.increaseBlockDepth();
    }
  }
 else   if ("END".equals(keyword.getText()) && !"ROW".equals(previousKeyword.getText())) {
    context.decreaseBlockDepth();
  }
}
