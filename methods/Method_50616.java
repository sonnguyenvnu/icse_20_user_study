@Override protected void handleSourceCode(final String source){
  if (!hasRealLoc()) {
    return;
  }
  char firstChar=source.charAt(node.getLoc().getStartIndex());
  curlyBrace=firstChar == '{';
}
