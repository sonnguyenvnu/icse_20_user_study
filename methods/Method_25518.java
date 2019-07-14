/** 
 * Given an ExpressionTree, removes any enclosing parentheses. 
 */
public static ExpressionTree stripParentheses(ExpressionTree tree){
  while (tree instanceof ParenthesizedTree) {
    tree=((ParenthesizedTree)tree).getExpression();
  }
  return tree;
}
