private static boolean parenthesizedChildHasKind(BinaryTree tree,Kind kind){
  Kind childKind=ASTHelpers.stripParentheses(tree.getLeftOperand() instanceof ParenthesizedTree ? tree.getLeftOperand() : tree.getRightOperand()).getKind();
  return childKind == kind;
}
