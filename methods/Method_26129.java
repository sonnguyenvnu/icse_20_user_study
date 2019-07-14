@SuppressWarnings("TreeToString") private static boolean isSameExpression(ExpressionTree leftTree,ExpressionTree rightTree){
  if (ASTHelpers.getSymbol(leftTree) != ASTHelpers.getSymbol(rightTree)) {
    return false;
  }
  String leftTreeTextRepr=stripPrefixIfPresent(leftTree.toString(),"this.");
  String rightTreeTextRepr=stripPrefixIfPresent(rightTree.toString(),"this.");
  return leftTreeTextRepr.contentEquals(rightTreeTextRepr);
}
