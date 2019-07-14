@Override protected void adjustBlockDepth(ParserContext context,List<Token> tokens,Token keyword){
  String lastKeyword=keyword.getText();
  if ("BEGIN".equals(lastKeyword)) {
    context.increaseBlockDepth();
  }
 else   if ("END".equals(lastKeyword)) {
    context.decreaseBlockDepth();
  }
}
