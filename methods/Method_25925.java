@Override public Description matchMethodInvocation(MethodInvocationTree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return NO_MATCH;
  }
  Set<String> forbiddenTypes=ILLEGAL.get(sym.getQualifiedName().toString());
  if (forbiddenTypes == null) {
    return NO_MATCH;
  }
  Type ownerType=sym.owner.type;
  for (  String forbiddenType : forbiddenTypes) {
    if (ASTHelpers.isSubtype(ownerType,state.getTypeFromString(forbiddenType),state)) {
      return describeMatch(tree);
    }
  }
  return NO_MATCH;
}
