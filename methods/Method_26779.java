@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  if (!PRINT_METHOD.matches(tree,state)) {
    return NO_MATCH;
  }
  Symbol base=tree.getMethodSelect().accept(new TreeScanner<Symbol,Void>(){
    @Override public Symbol visitIdentifier(    IdentifierTree node,    Void unused){
      return ASTHelpers.getSymbol(node);
    }
    @Override public Symbol visitMemberSelect(    MemberSelectTree node,    Void unused){
      return super.visitMemberSelect(node,null);
    }
  }
,null);
  if (!Objects.equals(base,state.getSymtab().systemType.tsym)) {
    return NO_MATCH;
  }
  ExpressionTree arg=Iterables.getOnlyElement(tree.getArguments());
  if (!STRING_FORMAT.matches(arg,state)) {
    return NO_MATCH;
  }
  List<? extends ExpressionTree> formatArgs=((MethodInvocationTree)arg).getArguments();
  return describeMatch(tree,SuggestedFix.builder().replace(((JCTree)tree).getStartPosition(),((JCTree)formatArgs.get(0)).getStartPosition(),"System.err.printf(").replace(state.getEndPosition((JCTree)getLast(formatArgs)),state.getEndPosition((JCTree)tree),")").build());
}
