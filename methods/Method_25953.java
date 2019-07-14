@Override public Description matchMethod(MethodTree method,VisitorState state){
  if (!CAN_BE_A_BINDS_METHOD.matches(method,state)) {
    return NO_MATCH;
  }
  JCClassDecl enclosingClass=ASTHelpers.findEnclosingNode(state.getPath(),JCClassDecl.class);
  if (!IS_DAGGER_2_MODULE.matches(enclosingClass,state)) {
    return NO_MATCH;
  }
  if (enclosingClass.getExtendsClause() != null) {
    return fixByDelegating();
  }
  for (  Tree member : enclosingClass.getMembers()) {
    if (member.getKind().equals(Tree.Kind.METHOD) && !getSymbol(member).isConstructor()) {
      MethodTree siblingMethod=(MethodTree)member;
      Set<Modifier> siblingFlags=siblingMethod.getModifiers().getFlags();
      if (!(siblingFlags.contains(Modifier.STATIC) || siblingFlags.contains(Modifier.ABSTRACT)) && !CAN_BE_A_BINDS_METHOD.matches(siblingMethod,state)) {
        return fixByDelegating();
      }
    }
  }
  return fixByModifyingMethod(state,enclosingClass,method);
}
