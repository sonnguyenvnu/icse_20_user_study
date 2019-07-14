/** 
 * Removes any enclosing parentheses from the tree. 
 */
public static Tree stripParentheses(Tree tree){
  while (tree instanceof ParenthesizedTree) {
    tree=((ParenthesizedTree)tree).getExpression();
  }
  return tree;
}
