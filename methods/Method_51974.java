private boolean isMethodCall(ASTPrimaryExpression node){
  boolean result=false;
  List<ASTPrimarySuffix> suffixes=node.findDescendantsOfType(ASTPrimarySuffix.class);
  if (suffixes.size() == 1) {
    result=suffixes.get(0).isArguments();
  }
  return result;
}
