static Operand classify(JCTree tree,VisitorState state){
  CharSequence source=state.getSourceForNode(tree);
  if (tree instanceof MethodInvocationTree) {
    MethodInvocationTree receiverInvocation=(MethodInvocationTree)tree;
    MethodSymbol sym=ASTHelpers.getSymbol(receiverInvocation);
    if (sym != null) {
      if (sym.getSimpleName().contentEquals("getClass") && sym.params().isEmpty()) {
        if (receiverInvocation.getMethodSelect() instanceof IdentifierTree) {
          return Operand.create(Kind.EXPR,state.getSourceForNode(tree),source);
        }
        return Operand.create(Kind.GET_CLASS,state.getSourceForNode((JCTree)ASTHelpers.getReceiver(receiverInvocation)),source);
      }
    }
  }
 else   if (tree instanceof MemberSelectTree) {
    MemberSelectTree select=(MemberSelectTree)tree;
    if (select.getIdentifier().contentEquals("class")) {
      return Operand.create(Kind.LITERAL,state.getSourceForNode((JCTree)select.getExpression()),source);
    }
  }
  return Operand.create(Kind.EXPR,source,source);
}
