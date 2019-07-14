private static boolean arrayLengthMatcher(MemberSelectTree tree,VisitorState state){
  return ASTHelpers.getSymbol(tree) == state.getSymtab().lengthVar;
}
