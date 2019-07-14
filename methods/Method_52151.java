private static boolean isMatch(Node node){
  return isUnaryNot(node) || isNotEquals(node) || isConditionalWithAllMatches(node) || isParenthesisAroundMatch(node);
}
