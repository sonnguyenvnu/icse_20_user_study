@Override public Description matchMemberReference(MemberReferenceTree tree,VisitorState state){
  Symbol.MethodSymbol sym=ASTHelpers.getSymbol(tree);
  if (sym == null) {
    return Description.NO_MATCH;
  }
  for (  Symbol.VarSymbol formalParam : sym.getParameters()) {
    if (hasCompileTimeConstantAnnotation(state,formalParam)) {
      return buildDescription(tree).setMessage("References to methods with @CompileTimeConstant parameters are not supported.").build();
    }
  }
  return Description.NO_MATCH;
}
