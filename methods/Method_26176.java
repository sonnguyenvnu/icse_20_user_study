@Override public Description matchMemberSelect(MemberSelectTree tree,VisitorState state){
  JCExpression receiverTree=(JCExpression)tree.getExpression();
  if (receiverTree == null || receiverTree.type == null || receiverTree.type.getKind() == TypeKind.PACKAGE) {
    return Description.NO_MATCH;
  }
  Symbol sym=getSymbol(tree);
  if ((tree instanceof JCFieldAccess) && (sym == null || sym.isStatic())) {
    return Description.NO_MATCH;
  }
  Description result=checkExpression(receiverTree,state,qual -> String.format("Dereferencing method/field \"%s\" of %s null receiver %s",tree.getIdentifier(),qual,receiverTree));
  return result != null ? result : Description.NO_MATCH;
}
