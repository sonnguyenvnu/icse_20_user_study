private boolean primaryExpressionsAreEqual(ASTPrimaryExpression nullCompareVariable,ASTPrimaryExpression expressionUsage){
  List<String> nullCompareNames=new ArrayList<>();
  findExpressionNames(nullCompareVariable,nullCompareNames);
  List<String> expressionUsageNames=new ArrayList<>();
  findExpressionNames(expressionUsage,expressionUsageNames);
  for (int i=0; i < nullCompareNames.size(); i++) {
    if (expressionUsageNames.size() == i) {
      return false;
    }
    String nullCompareExpressionName=nullCompareNames.get(i);
    String expressionUsageName=expressionUsageNames.get(i);
    if (!nullCompareExpressionName.equals(expressionUsageName) && !expressionUsageName.startsWith(nullCompareExpressionName + ".")) {
      return false;
    }
  }
  return true;
}
