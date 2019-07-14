/** 
 * Given a BinaryTree to match against and a list of two matchers, applies the matchers to the operands in both orders. If both matchers match, returns a list with the operand that matched each matcher in the corresponding position.
 * @param tree a BinaryTree AST node
 * @param matchers a list of matchers
 * @param state the VisitorState
 * @return a list of matched operands, or null if at least one did not match
 */
@Nullable public static List<ExpressionTree> matchBinaryTree(BinaryTree tree,List<Matcher<ExpressionTree>> matchers,VisitorState state){
  ExpressionTree leftOperand=tree.getLeftOperand();
  ExpressionTree rightOperand=tree.getRightOperand();
  if (matchers.get(0).matches(leftOperand,state) && matchers.get(1).matches(rightOperand,state)) {
    return Arrays.asList(leftOperand,rightOperand);
  }
 else   if (matchers.get(0).matches(rightOperand,state) && matchers.get(1).matches(leftOperand,state)) {
    return Arrays.asList(rightOperand,leftOperand);
  }
  return null;
}
