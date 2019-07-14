private static void replaceUseWithMethodReference(SuggestedFix.Builder fix,ExpressionTree node,String newName,VisitorState state){
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (parent instanceof MemberSelectTree && ((MemberSelectTree)parent).getExpression().equals(node)) {
    Tree receiver=node.getKind() == Tree.Kind.IDENTIFIER ? null : getReceiver(node);
    fix.replace(receiver != null ? state.getEndPosition(receiver) : ((JCTree)node).getStartPosition(),state.getEndPosition(parent),newName);
  }
 else {
    Symbol sym=getSymbol(node);
    fix.replace(node,String.format("%s::%s",sym.isStatic() ? sym.owner.enclClass().getSimpleName() : "this",newName));
  }
}
