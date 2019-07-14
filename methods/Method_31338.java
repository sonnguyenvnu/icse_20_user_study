@Override protected void adjustBlockDepth(ParserContext context,List<Token> tokens,Token keyword){
  int lastKeywordIndex=getLastKeywordIndex(tokens);
  Token previousKeyword=lastKeywordIndex >= 0 ? tokens.get(lastKeywordIndex) : null;
  if ("BEGIN".equals(keyword.getText()) || (("IF".equals(keyword.getText()) || "FOR".equals(keyword.getText()) || "CASE".equals(keyword.getText())) && previousKeyword != null && !"END".equals(previousKeyword.getText()))) {
    context.increaseBlockDepth();
  }
 else   if (("EACH".equals(keyword.getText()) || "SQLEXCEPTION".equals(keyword.getText())) && previousKeyword != null && "FOR".equals(previousKeyword.getText())) {
    context.decreaseBlockDepth();
  }
 else   if ("END".equals(keyword.getText())) {
    context.decreaseBlockDepth();
  }
}
