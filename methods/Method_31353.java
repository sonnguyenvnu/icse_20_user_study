@Override protected void adjustBlockDepth(ParserContext context,List<Token> tokens,Token keyword){
  int lastKeywordIndex=getLastKeywordIndex(tokens);
  if (lastKeywordIndex < 0) {
    return;
  }
  String current=keyword.getText();
  if ("FUNCTION".equals(current) || "PROCEDURE".equals(current)) {
    String previous=tokens.get(lastKeywordIndex).getText();
    if ("CREATE".equals(previous) || "DBA".equals(previous)) {
      context.increaseBlockDepth();
    }
 else     if ("END".equals(previous)) {
      context.decreaseBlockDepth();
    }
  }
}
