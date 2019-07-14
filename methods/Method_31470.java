@Override protected void adjustBlockDepth(ParserContext context,List<Token> tokens,Token keyword){
  int lastKeywordIndex=getLastKeywordIndex(tokens);
  if (lastKeywordIndex < 0) {
    return;
  }
  Token previousKeyword=tokens.get(lastKeywordIndex);
  if (("BEGIN".equals(keyword.getText()) || "DO".equals(keyword.getText()) || "IF".equals(keyword.getText()) && !"END".equals(previousKeyword.getText()))) {
    context.increaseBlockDepth();
  }
 else   if ("END".equals(keyword.getText())) {
    context.decreaseBlockDepth();
  }
}
